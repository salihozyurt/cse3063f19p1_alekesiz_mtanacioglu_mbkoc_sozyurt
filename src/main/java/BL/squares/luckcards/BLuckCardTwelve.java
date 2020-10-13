package BL.squares.luckcards;

import DAL.DPlayer;

public class BLuckCardTwelve extends BLuckCards {

    public BLuckCardTwelve() {
        setCARD_INFO("Pay poor tax of $15. ");
        setCardID(12);
    }

    @Override
    public void performForCard(DPlayer dPlayer) {
        dPlayer.setBalance(dPlayer.getBalance() - 15);
    }
}
