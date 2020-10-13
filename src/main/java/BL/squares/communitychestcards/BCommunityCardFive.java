package BL.squares.communitychestcards;

import DAL.DPlayer;

public class BCommunityCardFive extends BCommunityCards {
    private DPlayer cardOwner;

    public BCommunityCardFive(){
        setCARD_INFO("Get Out of Jail Free. This card may be kept until needed or sold/traded.");
        setCardID(5);
    }

    @Override
    public void performForCard(DPlayer dPlayer) {
        setCardOwner(dPlayer);
    }

    public DPlayer getCardOwner() {
        return cardOwner;
    }

    public void setCardOwner(DPlayer dPlayer) {
        this.cardOwner = dPlayer;
    }

}
