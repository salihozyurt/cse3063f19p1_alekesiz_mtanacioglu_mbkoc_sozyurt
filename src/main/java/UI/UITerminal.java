package UI;

import BL.BPlayer;
import BL.squares.BPropertySquare;
import BL.squares.BSquare;
import Controller.CInstruction;
import DAL.DPlayer;

import java.util.ArrayList;
import java.util.Iterator;


/**
 *  <h>UITerminal</h>
 *
 *  <p>UITerminal is the class to print all data of Monopoly Game.</p>
 *
 * @author Salih ÖZYURT
 * @version 1.0
 */

public class UITerminal {

    private String UINameOfCard;
    private static UITerminal instance;

    private UITerminal() {

    }

    /**
     * <p>This class is made singleton </p>
     *
     * @return void
     */
    public static UITerminal getInstance() {
        if (instance == null) {
            instance = new UITerminal();
        }
        return instance;
    }

    /**
     * <p>The method prints json file that we decide all properties of Monopoly Game. </p>
     * @return void
     */
    public void printCurrentJSONFile() {
        System.out.println("\033[31m  -----|||    |||-------||||||---|||   ||---||||||---||||||----||||||---||------||  ||- \033[0m");
        System.out.println("\033[31m  ----|| ||  || ||-----||    ||--||||  ||--||    ||--||   ||--||    ||--||-------||||-- \033[0m");
        System.out.println("\033[31m  ---||   ||||   ||----||    ||--|| || ||--||    ||--||||||---||    ||--||--------||--- \033[0m");
        System.out.println("\033[31m  --||     ||     ||---||    ||--||  ||||--||    ||--||-------||    ||--||--------||--- \033[0m");
        System.out.println("\033[31m  -||              ||---||||||---||   |||---||||||---||--------||||||---|||||||---||--- \033[0m");
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("PROPERTIES OF THE GAME");
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println(CInstruction.getDInstruction());
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("MONOPOLY GAME");
        System.out.println("---------------------------------------------------------------------------------------");
    }

    /**
     * <p>The method prints data of players berofe roll dice.</p>
     *
     * @param bPlayer We use the parameter to reach data of players.
     * @return void
     */
    public void printBeforeRollDice(BPlayer bPlayer) {
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("Total Rounds: " + bPlayer.getDPlayer().getTotalRounds());
        System.out.println("Player Type :" + bPlayer.getDPlayer().getPieceType());
        System.out.println("Player Turn Counter :" + bPlayer.getDPlayer().getRoundValue());
        System.out.println("Player Cycle Counter :" + bPlayer.getDPlayer().getCycleCounter());
        System.out.println("Location Square :" + bPlayer.getDPlayer().getLocation());
        System.out.println("Player Current Balance :" + bPlayer.getDPlayer().getBalance());
        System.out.println("---------------------------------------------------------------------------------------");
    }

    /**
     * <p>The method prints dice face and total dice values after current player roll dice.</p>
     *
     * @param diceValues The parameter contains dice values.
     * @return void
     */
    //TODO Name of player will be added.
    public void printDicesFaces(int[] diceValues, BPlayer bPlayer){
        System.out.println("Rolling Dice : " + diceValues[0] + "+" + diceValues[1] + "=" + (diceValues[0] + diceValues[1]));
    }

    /**
     * <p>The method prints data of community and luck cards after roll dice.</p>
     *
     * @param nameOfCard We use the parameter to reach data of community and luck cards.
     * @return void
     */
    public void printCard(String nameOfCard){
        this.UINameOfCard = nameOfCard;
    }

    /**
     * <p>The method prints data of players after roll dice.</p>
     *
     * @param bPlayer We use the parameter to reach data of players.
     * @return void
     */
    public void printAfterRollDice(BPlayer bPlayer, BSquare bSquare){
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("Square Type : " + bSquare.getSQUARE_TYPE());
        System.out.println("Player Location : " + bPlayer.getDPlayer().getLocation());
        System.out.println("Oyuncu:"+bPlayer.getDPlayer().getPieceType());
        System.out.println("Oyuncunun yeri:"+bPlayer.getDPlayer().roundCounter);
        System.out.println("Color Of Location : " + bSquare.pType);
        switch (bSquare.getSQUARE_TYPE()){
            case "PROPERTY_SQUARE":
                System.out.println("Name Of Location : " + ((BPropertySquare) bSquare).getName());
                System.out.print("Owner Of Location : ");
                System.out.println(bSquare.getOwnerOfSquare() != null ? bSquare.getOwnerOfSquare().getDPlayer().getPieceType() : "None");
                System.out.println("Rent Of Location : " + bSquare.rent);
                break;
            case "LUCK_SQUARE":
                System.out.println("Property Of Luck Card : " + UINameOfCard);
                break;
            case "COMMUNITY_SQUARE":
                System.out.println("Property Of Community Card : " + UINameOfCard);
                break;
            default:
                break;
        }
        System.out.println("Player Balance After Rolling Dice : " + bPlayer.getDPlayer().getBalance());
        System.out.println("---------------------------------------------------------------------------------------");
    }

    /**
     * <p>The method prints data of player and square where player rent.</p>
     *
     * @param pieceType We use the parameter to reach data of player.
     * @param bSquare We use the parameter to reach data of square.
     *
     * @return void
     */
    public void printRentProcess(String pieceType, BSquare bSquare){
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("\033[31m"+ pieceType + " is paying " + bSquare.getRent() + "$ to " + bSquare.getOwnerOfSquare().getDPlayer().getPieceType() + " on " + ((BPropertySquare) bSquare).getName() +". \033[0m");
    }

    /**
     * <p>The method prints data of player and square where player buy.</p>
     *
     * @param bPlayer We use the parameter to reach data of player.
     * @param bSquare We use the parameter to reach data of square.
     *
     * @return void
     */
    public void printBuyProcess(BPlayer bPlayer, BSquare bSquare){
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("\033[31m" + bPlayer.getDPlayer().getPieceType() + " is buying " + ((BPropertySquare) bSquare).getName() + ". \033[0m");
    }

    /**
     * <p>The method prints data of winner player.</p>
     *
     * @param bPlayer We use the parameter to reach data of winner player.
     * @return void
     */
    public void printWinnerPlayer(BPlayer bPlayer){
        System.out.println("*************************************************************************");
        System.out.println("GAME OVER!!!");
        System.out.println("*************************************************************************");
        System.out.println("WINNER PLAYER!!!");
        System.out.println("*************************************************************************");
        System.out.println("Player Name :" + bPlayer.getDPlayer().getPieceType());
        System.out.println("Player Location :" + bPlayer.getDPlayer().getLocation());
        System.out.println("Player Balance :" + bPlayer.getDPlayer().getBalance());
        System.out.println("*************************************************************************");
    }

    /**
     * <p>In the method, we sort the eliminated players for balance and print data of the players.</p>
     *
     * @param eliminatedList We use the parameter to reach data of eliminated players. The arraylist includes eliminated players.
     * @return void
     */
    public void printGameOver(ArrayList eliminatedList){
        System.out.println("ELIMINATED PLAYERS!!!");
        System.out.println("*************************************************************************");
        for (Iterator<BPlayer> iterator = eliminatedList.iterator(); iterator.hasNext();) {
            BPlayer eliminatedPlayer = iterator.next();
            System.out.println("Player Name :" + eliminatedPlayer.getDPlayer().getPieceType());
            System.out.println("Player Location :" + eliminatedPlayer.getDPlayer().getLocation());
            System.out.println("Player Balance :" + eliminatedPlayer.getDPlayer().getBalance());
            System.out.println("*************************************************************************");
        }
    }

    public void printHouseInfo(BPlayer bPlayer, BSquare bSquare){
        System.out.println("\033[31m  "+ bPlayer.getDPlayer().getPieceType() +" make house on "+ ((BPropertySquare) bSquare).getName() +" \033[0m");
    }

    public void printHotelInfo(BPlayer bPlayer, BSquare bSquare){
        System.out.println("\033[31m  "+ bPlayer.getDPlayer().getPieceType() +" make hotel on "+ ((BPropertySquare) bSquare).getName() +" \033[0m");
    }
}