package BL.squares.luckcards;

import DAL.DPlayer;

public class BLuckCardNine extends BLuckCards {

    public BLuckCardNine() {
        setCARD_INFO("Go Back Three Spaces. ");
        setCardID(9);
    }

    @Override
    public void performForCard(DPlayer dPlayer) {
        dPlayer.setTotalDiceValue(dPlayer.getTotalDiceValue() - 3);
    }
}
