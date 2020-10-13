package BL.squares;

import BL.BTerminal;
import BL.squares.luckcards.*;
import DAL.DPlayer;

import java.util.ArrayList;

/**
 *  <h>BLuckSquare</h>
 *
 *  <p>This class contain property player must makes when player comes to luck property square.</p>
 *
 * @author Salih ÖZYURT
 * @version 1.0
 */

public class BLuckSquare extends BSquare {
    private final String SQUARE_TYPE = "LUCK_SQUARE";
    private ArrayList<BLuckCards> luckCards = new ArrayList<>();
    private int totalNumberCard = 16;
    private BTerminal bTerminal = new BTerminal();

    /**
     * <p>Constructor make initialize the card and property type. </p>
     */
    public BLuckSquare(PropertyType propertyType) {
        this.pType = propertyType;
        initLuckCards();
    }

    /**
     * <p>This method add luck card deck in arraylist. </p>
     *
     * @return void
     */
    private void initLuckCards(){
        luckCards.add(new BLuckCardOne());
        luckCards.add(new BLuckCardTwo());
        luckCards.add(new BLuckCardThree());
        luckCards.add(new BLuckCardFour());
        luckCards.add(new BLuckCardFive());
        luckCards.add(new BLuckCardSix());
        luckCards.add(new BLuckCardSeven());
        luckCards.add(new BLuckCardEigth());
        luckCards.add(new BLuckCardNine());
        luckCards.add(new BLuckCardTen());
        luckCards.add(new BLuckCardEleven());
        luckCards.add(new BLuckCardTwelve());
        luckCards.add(new BLuckCardThirteen());
        luckCards.add(new BLuckCardFourteen());
        luckCards.add(new BLuckCardFifteen());
        luckCards.add(new BLuckCardSixteen());
    }

    @Override
    public void performOnLand(DPlayer player) {
        int temp = (int) (Math.random() * totalNumberCard);
        bTerminal.printCard(luckCards.get(temp).setCARD_INFO());
        luckCards.get(temp).performForCard(player);
    }

    @Override
    public String getSQUARE_TYPE() {
        return this.SQUARE_TYPE;
    }
}
