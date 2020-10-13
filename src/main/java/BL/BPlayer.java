package BL;

import BL.squares.*;
import DAL.DPlayer;
import spring.config.squares.*;
import spring.consumer.SquareConsumer;
import spring.factory.BeanCreator;

/***
 *BPlayer is player class in Business Layer.
 * All methods of players implemented here.
 *
 * @author Mücahit Tanacıoğlu
 * @version 1.0
 * @since 11.
 */

public class BPlayer implements BPlayerObserver {

    private DPlayer dPlayer;
    private BTerminal bTerminal = new BTerminal();

    public BPlayer(DPlayer dPlayer) {
        this.dPlayer = dPlayer;
    }

    /**
     *<p>This method get calls after each player roll dice, it takes current dice value of player and the square player going to move.</p>
     * <p>Checks player whether bankrupted or not and calls corresponding performOnLand method for currentSquare.</p>
     *
     * @param currentDiceValue The  total dice value after player roll dice.
     * @param currentSquare A BSquare object which is player going to move.
     *
     * @return void
     */
    @Override
    public void checkAndUpdatePlayer(int currentDiceValue, BSquare currentSquare) {
        getDPlayer().setRoundValue(getDPlayer().getRoundValue() + 1);
        getDPlayer().setTotalRounds(getDPlayer().getTotalRounds() + 1);
        if (getDPlayer().getCycleCounter() == 0 && currentSquare instanceof BGoSquare) return;
        if (isPlayerCrossTheGoSquare()) {
            dPlayer.setCycleCounter(dPlayer.getCycleCounter() + 1);
            new BGoSquare(PropertyType.NOCOLOR).performOnLand(dPlayer);
            if (currentSquare instanceof BGoSquare) return;
        }
            new SquareConfiguration().setSquare(currentSquare);
            BeanCreator.instance.createBean(SquareConfiguration.class, SquareConsumer.class)
                    .conductAction(dPlayer);

        if (isPlayerBankrupted()) {
            if(!tryToSellProperty(currentSquare)){
                dPlayer.setBalance(0);
                dPlayer.setBankruptFlag(true);
                dPlayer.setBalance(0);
            }
        }
    }
    /**
     *<p>This method get calls after player makes move It hold players money for each turn to draw its money progress in time.</p>
     *
     * @param turn An int value hold players turn.
     * @param money An int value holds players current money.
     * @return void
     */
    public void updateDataset(int turn, int money) {
        dPlayer.getPlayerDataset().add(turn, money);
    }
    /**
     *<p>This method get calls after player has to pay something but does not have enough money to progress.</p>
     * <p>Checks player has properties to sell if does then sells them till it has enough money to progress, if not player get bankrupt.</p>
     *
     * @param currentSquare A BSquare object which is player going to move.
     *
     * @return boolean A boolean value indicates player bankrupted or not.
     */
    boolean tryToSellProperty(BSquare currentSquare) {
        if (!dPlayer.getPropertySquares().isEmpty()) {
            int debt = currentSquare.rent;
            int currentPrice = this.getDPlayer().getBalance();
            for (int i = 0; i < dPlayer.getPropertySquares().size() && currentPrice < debt; ++i) {
                currentPrice += dPlayer.getPropertySquares().get(i).price;
                sellSquare(dPlayer.getPropertySquares().get(i));
            }
            return currentPrice >= debt;
        } else {
            return false;
        }
    }
    /**
     *<p>This method get calls after player has to sell its properties.</p>
     * <p>Checks squares price and add it to player balance and sets owner of square null.</p>
     *
     * @param square A BSquare object which player bought before.
     *
     * @return void
     */
    private void sellSquare(BSquare square) {
        square.setOwnerOfSquare(null);
        this.getDPlayer().setHasFullColor(((BPropertySquare) square).pType.getValue(), this.getDPlayer().getPieceType().getValue(),
                this.getDPlayer().getHasFullColor(((BPropertySquare) square).pType.getValue(),this.getDPlayer().getPieceType().getValue()) - 1);
        this.getDPlayer().setBalance(this.getDPlayer().getBalance() + square.price);
        if ( ((BPropertySquare) square).getHasHotel()){
            square.setPrice(square.getPrice() / 7);
            square.setRent(square.getRent() / 4);
        }
        else if ( ((BPropertySquare) square).getHasHouse()){
            square.setPrice(square.getPrice() / 3);
            square.setRent(square.getRent() / 2);
        }
    }

    /**
     * Rolls a dice. Faces cannot be the same.
     * @return int
     */
    public int rollDice(){
        int[] diceValues;
        diceValues = this.getDPlayer().getPlayerDice().rollDice();
        this.getDPlayer().setCurrentDiceVal(diceValues[0] + diceValues[1]);
        bTerminal.printDicesFaces(diceValues, this);
        return diceValues[0] + diceValues[1];
    }

    /**
     *<p>Checks whether player pass the start square.</p>
     * @return boolean
     */
    private boolean isPlayerCrossTheGoSquare() {
        return (int) Math.floor(dPlayer.getTotalDiceValue() / 40f) != dPlayer.getCycleCounter();
    }

    /**
     * <p>Checks whether player bankrupted or not.</p>
     * @return boolean
     */
    private boolean isPlayerBankrupted() {
        return dPlayer.getBalance() < 0;
    }

    public boolean buy(BPropertySquare currentSquare){
            int price = currentSquare.getPrice();
            this.getDPlayer().setBalance(this.getDPlayer().getBalance() - price);
            this.getDPlayer().addPropertySquares(currentSquare);
            this.sortSquares();
            this.getDPlayer().setHasFullColor(currentSquare.pType.getValue(),this.getDPlayer().getPieceType().getValue(),
                    this.getDPlayer().getHasFullColor(currentSquare.pType.getValue(),this.getDPlayer().getPieceType().getValue()) + 1);
            return true;
    }
    /**
     * <p>Checks whether player can satisfy squares price.</p>
     * @return boolean
     */
    public boolean isAbleToBuy(BPropertySquare currentSquare){
        int price = currentSquare.getPrice();
        return this.getDPlayer().getBalance() >= price;
    }
    /**
     * <p>Sorts players properties by their price.</p>
     * @return void
     */
    public void sortSquares(){
        this.getDPlayer().getPropertySquares().sort((firstSquare, secondSquare) -> {
            if (firstSquare.getPrice() == secondSquare.getPrice())
                return 0;
            return firstSquare.getPrice() > secondSquare.getPrice() ? -1 : 1;
        });
    }

    public DPlayer getDPlayer() {
        return dPlayer;
    }

    public boolean isAbleToBuilt(BPropertySquare currentSquare){
        int price = currentSquare.getPrice();
        return this.getDPlayer().getBalance() >= 2*price;
    }

}
