package BL.squares.luckcards;

import DAL.DInstruction;
import DAL.DPlayer;

public class BLuckCardTwo extends BLuckCards {

    public BLuckCardTwo() {
        setCARD_INFO("Advance to Go Square.");
        setCardID(2);
    }

    @Override
    public void performForCard(DPlayer dPlayer) {
        dPlayer.setTotalDiceValue(40 - dPlayer.getLocation() + dPlayer.getTotalDiceValue());
        dPlayer.setBalance(dPlayer.getBalance() + (int) DInstruction.getInstance().priceOfGoSquare);
    }
}
