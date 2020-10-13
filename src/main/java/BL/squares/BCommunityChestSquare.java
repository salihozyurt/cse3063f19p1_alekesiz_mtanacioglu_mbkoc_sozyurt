package BL.squares;

import BL.BTerminal;
import BL.squares.communitychestcards.*;
import DAL.DPlayer;

import java.util.ArrayList;

/**
 *  <h>BCommunityChestSquare</h>
 *
 *  <p>This class contain property player must makes when player comes to community chest property square.</p>
 *
 * @author Salih ÖZYURT
 * @version 1.0
 */

public class BCommunityChestSquare extends BSquare {
    private final String SQUARE_TYPE = "COMMUNITY_SQUARE";
    private ArrayList<BCommunityCards> communityChestCards = new ArrayList<>();
    private int totalNumberCard = 17;
    private BTerminal bTerminal = new BTerminal();

    /**
     * <p>Constructor make initialize the card and property type. </p>
     */
    public BCommunityChestSquare(PropertyType propertyType) {
        this.pType = propertyType;
        initCommunityCards();
    }

    /**
     * <p>This method add community card deck arraylist. </p>
     *
     * @return void
     */
    private void initCommunityCards(){
        communityChestCards.add(new BCommunityCardOne());
        communityChestCards.add(new BCommunityCardTwo());
        communityChestCards.add(new BCommunityCardThree());
        communityChestCards.add(new BCommunityCardFour());
        communityChestCards.add(new BCommunityCardFive());
        communityChestCards.add(new BCommunityCardSix());
        communityChestCards.add(new BCommunityCardSeven());
        communityChestCards.add(new BCommunityCardEigth());
        communityChestCards.add(new BCommunityCardNine());
        communityChestCards.add(new BCommunityCardTen());
        communityChestCards.add(new BCommunityCardEleven());
        communityChestCards.add(new BCommunityCardTwelve());
        communityChestCards.add(new BCommunityCardThirteen());
        communityChestCards.add(new BCommunityCardFourteen());
        communityChestCards.add(new BCommunityCardFifteen());
        communityChestCards.add(new BCommunityCardSixteen());
        communityChestCards.add(new BCommunityCardSeventeen());
    }

    @Override
    public void performOnLand(DPlayer player) {
        int temp = (int) (Math.random() * totalNumberCard);
        bTerminal.printCard(communityChestCards.get(temp).setCARD_INFO());
        communityChestCards.get(temp).performForCard(player);
    }

    @Override
    public String getSQUARE_TYPE() {
        return this.SQUARE_TYPE;
    }
}
