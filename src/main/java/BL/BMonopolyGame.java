package BL;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import BL.squares.BPropertySquare;
import Controller.Main;
import DAL.DPlayer;
import DAL.DInstruction;
import DAL.DPiece;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import spring.log.Logger;

/**
 * <p>BMonopolyGame is MonopolyGame class in Business Layer. Main rules
 * of the game implemented in this class.</p>
 *
 * @author Mücahit Tanacıoğlu
 * @version 1.0
 */
public class BMonopolyGame implements BGameObserver {

    private static BMonopolyGame monopolyGameInstance = new BMonopolyGame();
    private ArrayList<BL.BPlayer> currentPlayers, eliminatedPlayers;
    private BBoard boardInstance;
    private BTerminal bTerminal = new BTerminal();
    private ArrayList<Integer> diceSumOfPlayers = new ArrayList<>();
    private XYSeriesCollection moneyDataset = new XYSeriesCollection();

    public BMonopolyGame() {
        currentPlayers = new ArrayList<>();
        boardInstance = BBoard.getInstance();
        eliminatedPlayers = new ArrayList<>();
    }

    public static BMonopolyGame getInstance() {
        return monopolyGameInstance;
    }

    /**
     *<p>Takes initial data of the game and passing it into initPlayersByLettingThemRollingDiceAndPutInList method
     *for creating players and putting them into a list.</p>
     * <p>Listen method runs to watch the player.
     * This method runs until just one player left.</p>
     *
     * @param gameInstructions A DInstruction object which is contain initial data for game.
     * @return void
     */
    public void startGame(DInstruction gameInstructions){
        initPlayersByLettingThemRollingDiceAndPutInList(gameInstructions);
        listen();
    }

    /**
     *<p>Takes initial data of the game and create players, after that to arrange order of players, makes them roll dice</p>
     * <p>Listen method runs to watch the player.
     * This method runs until just one player left.</p>
     *
     * @param gameInstructions A DInstruction object which is contain initial data for game.
     * @return void
     */

    private void initPlayersByLettingThemRollingDiceAndPutInList(DInstruction gameInstructions) {
        int counter = (int) gameInstructions.countOfPlayers;
        int diceSum;

        while(counter != 0){
            currentPlayers.add(new BL.BPlayer(new DPlayer(DPiece.PieceType.BATTLESHIP, (int) gameInstructions.startMoney)));
            counter--;
        }

        //Rolling dice for each players.
        for (BL.BPlayer player : currentPlayers) {
            diceSum = player.rollDice();
            //Checking if the diceSum same with other players.
            while(checkIfDiceSumExist(diceSumOfPlayers, diceSum)){
                diceSum = player.rollDice();
            }
            diceSumOfPlayers.add(diceSum);
        }

        //Sorting player list by theirs currentDiceVal properties by decrementing order.
        currentPlayers.sort((firstPlayer, secondPlayer) -> {
            if (firstPlayer.getDPlayer().getCurrentDiceVal() == secondPlayer.getDPlayer().getCurrentDiceVal())
                return 0;
            return firstPlayer.getDPlayer().getCurrentDiceVal() > secondPlayer.getDPlayer().getCurrentDiceVal() ? -1 : 1;
        });
        //After sorting players the piece types are setting.
        for(int i = 0; i < currentPlayers.size(); i++){
            currentPlayers.get(i).getDPlayer().setPieceType(DPiece.PieceType.values()[i]);
            currentPlayers.get(i).getDPlayer().setPlayerDataset(DPiece.PieceType.values()[i]);
            currentPlayers.get(i).updateDataset(0,(int)gameInstructions.startMoney);
        }
    }

    /**
     *<p>Returns true if diceSum exist before, returns false if diceSum
     * does not exist in the list.</p>
     *
     * @param diceSumOfPlayers list of diceSum for each players in the game.
     * @param diceSum integer value of dice sum of current player.
     * @return boolean
     */
    private boolean checkIfDiceSumExist(ArrayList<Integer> diceSumOfPlayers, int diceSum){
        for (Integer diceSumOfPlayer : diceSumOfPlayers) {
            if (diceSumOfPlayer.equals(diceSum)) {
                return true;
            }
        }
        return false;
    }

    /**
     *<p>Starts game turn and if a player eliminated ,stores them.</p>
     * <p>Listen method runs to watch the player.
     * This method runs until just one player left.</p>
     *@return void
     */
    @Override
    public void listen() {
        int currentRoundIndex = 0;
        while (currentPlayers.size() != 1 && currentRoundIndex++ < Main.ROUND_LIMIT) {
            startTurn();
        }
        ArrayList<BPlayer> playersGroup = new ArrayList<>(currentPlayers);
        playersGroup.addAll(eliminatedPlayers);
        for (BPlayer player: playersGroup) {
            moneyDataset.addSeries(player.getDPlayer().getPlayerDataset());
        }
        PlayersChart playersChart = new PlayersChart("Monopoly", "Players/Money Line Chart");
        playersChart.pack();
        playersChart.setVisible(true);
        System.out.println("Finished");
        eliminatedPlayers.sort((firstPlayer, secondPlayer) -> {
            if(firstPlayer.getDPlayer().getBalance() == secondPlayer.getDPlayer().getBalance()) return 0;
            return firstPlayer.getDPlayer().getBalance() > secondPlayer.getDPlayer().getBalance() ? -1 : 1;
        });
        Logger.instance.LOGGER.log(Logger.DEFAULT_LEVEL, "Game is finished.");
        Logger.instance.LOGGER.log(Logger.DEFAULT_LEVEL, String.format("Winner: [%s]",
                currentPlayers.get(0).getDPlayer().getPieceType()));
        bTerminal.printWinnerPlayer(currentPlayers.get(0));
        bTerminal.printGameOver(eliminatedPlayers);
    }
    /**
     *<p>Each player plays their turn by their order, if player get bankrupt removes from list.</p>
     * <p>Listen method runs to watch the player.
     * This method runs until just one player left.</p>
     *@return void
     */
    private void startTurn() {
        BL.BPlayer currentPlayer;
        for (Iterator<BL.BPlayer> iterator = currentPlayers.iterator(); iterator.hasNext();) {
            currentPlayer = iterator.next();
            if (!currentPlayer.getDPlayer().isBankrupted()) {
                bTerminal.printBeforeRollDice(currentPlayer);
                if (currentPlayer.getDPlayer().isArrested()) {
                    if (boardInstance.getJailSquares().get(0).getJailRecords().containsKey(currentPlayer.getDPlayer())) {
                        boardInstance.getJailSquares().get(0).scanPlayerRecord(currentPlayer.getDPlayer());
                    } else if (boardInstance.getJailSquares().get(1).getJailRecords().containsKey(currentPlayer.getDPlayer())) {
                        boardInstance.getJailSquares().get(1).scanPlayerRecord(currentPlayer.getDPlayer());
                    }
                }
                if (!currentPlayer.getDPlayer().isArrested()) {
                    currentPlayer.rollDice();
                    currentPlayer.getDPlayer().setTotalDiceValue(currentPlayer.getDPlayer().getTotalDiceValue() +
                            currentPlayer.getDPlayer().getCurrentDiceVal());
                    BL.squares.BSquare currentSquare = boardInstance.getSquares()[currentPlayer.getDPlayer().getLocation()];
                    currentPlayer.checkAndUpdatePlayer(currentPlayer.getDPlayer().getCurrentDiceVal(),
                            currentSquare);
                    //Check house or hotel can built
                    //Building house or hotel
                    if (currentSquare.getOwnerOfSquare() == currentPlayer){
                        if(!((BPropertySquare)currentSquare).getHasHouse() &&
                                currentPlayer.isAbleToBuilt((BPropertySquare)currentSquare) &&
                                !currentPlayer.getDPlayer().controlHasFullColor(currentSquare.pType.getValue(),currentPlayer.getDPlayer().getPieceType().getValue()))
                        {
                            ((BPropertySquare)currentSquare).buildHouse();
                            currentPlayer.getDPlayer().setBalance(currentPlayer.getDPlayer().getBalance() - ((BPropertySquare)currentSquare).getHousePrice());
                            bTerminal.printHouseInfo(currentPlayer,currentSquare);
                        } else if(!((BPropertySquare)currentSquare).getHasHotel() &&
                                currentPlayer.isAbleToBuilt((BPropertySquare)currentSquare))
                        {
                            ((BPropertySquare)currentSquare).buildHotel();
                            currentPlayer.getDPlayer().setBalance(currentPlayer.getDPlayer().getBalance() - ((BPropertySquare)currentSquare).getHotelPrice());
                            bTerminal.printHotelInfo(currentPlayer,currentSquare);
                        }
                    }
                    //Calling buying function.
                    if(currentSquare.getOwnerOfSquare() == null &&
                            currentSquare.getSQUARE_TYPE().equals("PROPERTY_SQUARE") &&
                            currentPlayer.isAbleToBuy((BPropertySquare) currentSquare))
                    {
                        bTerminal.printBuyProcess(currentPlayer,currentSquare);
                        currentPlayer.buy((BPropertySquare) currentSquare);
                        currentSquare.setOwnerOfSquare(currentPlayer);
                    }

                    bTerminal.printAfterRollDice(currentPlayer, currentSquare);

                }
                currentPlayer.updateDataset(currentPlayer.getDPlayer().roundCounter++, currentPlayer.getDPlayer().getBalance());
                if (currentPlayer.getDPlayer().isBankrupted()) {
                    Logger.instance.LOGGER.log(Logger.DEFAULT_LEVEL, "Player: " + currentPlayer.getDPlayer().getPieceType() +
                            " is eliminated Round Value: " + (currentPlayer.getDPlayer().roundCounter - 1));
                    eliminatedPlayers.add(currentPlayer);
                    iterator.remove();
                }
                if(currentPlayers.size() ==1)
                    return;
            }
        }

    }

    public ArrayList<BPlayer> getPlayers(){
        return this.currentPlayers;
    }

    public BTerminal getBTerminal() {
        return bTerminal;
    }

    private class PlayersChart extends ApplicationFrame {

        public PlayersChart(String applicationTitle , String chartTitle ) {
            super(applicationTitle);
            JFreeChart lineChart = ChartFactory.createXYLineChart(
                    chartTitle,
                    "Time","Money",
                    moneyDataset,
                    PlotOrientation.VERTICAL,
                    true,true,false);

            ChartPanel chartPanel = new ChartPanel( lineChart );
            chartPanel.setPreferredSize( new java.awt.Dimension( 720 , 480 ) );
            setContentPane( chartPanel );
        }
    }
}
