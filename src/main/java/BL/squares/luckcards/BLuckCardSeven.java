package BL.squares.luckcards;

import DAL.DPlayer;

public class BLuckCardSeven extends BLuckCards {

    public BLuckCardSeven() {
        setCARD_INFO("Bank pays you dividend of $50. ");
        setCardID(7);
    }

    @Override
    public void performForCard(DPlayer dPlayer) {
        dPlayer.setBalance( dPlayer.getBalance() + 50);
    }
}
