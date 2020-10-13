package BL.squares.luckcards;

import DAL.DPlayer;

/**
 *  <h>BLuckCards</h>
 *
 *  <p>This class is abstract contain property and method for luck cards .</p>
 *
 * @author Salih ÖZYURT
 * @version 1.0
 */

public abstract class BLuckCards {
    private String CARD_INFO;
    private int cardID;

    /**
     * <p>This method is abstract in order luck cards to make their action </p>
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
