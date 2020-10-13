package BL.squares.luckcards;

import DAL.DPlayer;

public class BLuckCardEigth extends BLuckCards {
    private DPlayer cardOwner;

    public BLuckCardEigth() {
        setCARD_INFO("Get out of Jail Free. This card may be kept until needed. ");
        setCardID(8);
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
