package BL.squares.communitychestcards;

import DAL.DPlayer;

/**
 *  <h>BCommunityCards</h>
 *
 *  <p>This class is abstract contain property and method for community chest cards .</p>
 *
 * @author Salih ÖZYURT
 * @version 1.0
 */

public abstract class BCommunityCards {
    private String CARD_INFO;
    private int cardID;

    /**
     * <p>This method is abstract in order community chest cards to make their action </p>
     *
     * @param dPlayer We use the parameter to reach current Player.
     * @return void
     */
    public abstract void performForCard(DPlayer dPlayer);

    public int getCardID() {
        return cardID;
    }

    public void setCardID(int cardID) {
        this.cardID = cardID;
    }

    public String setCARD_INFO() {
        return CARD_INFO;
    }

    public void setCARD_INFO(String CARD_INFO) {
        this.CARD_INFO = CARD_INFO;
    }
}
