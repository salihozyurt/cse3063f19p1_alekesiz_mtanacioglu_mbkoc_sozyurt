package BL.squares.luckcards;

import DAL.DPlayer;

public class BLuckCardSix extends BLuckCards {

    public BLuckCardSix() {
        setCARD_INFO("Advance token to the nearest Railroad and pay owner twice the rental to which" +
                     " he/she is otherwise entitled. If Railroad is unowned, you may buy it from the Bank.");
        setCardID(6);
    }

    @Override
    public void performForCard(DPlayer dPlayer) {

    }
}
