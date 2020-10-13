package BL.squares.luckcards;

import DAL.DInstruction;
import DAL.DPlayer;

public class BLuckCardFour extends BLuckCards {

    public BLuckCardFour() {
        setCARD_INFO("Advance to Raison D'etre. If you pass Go, collect $200. ");
        setCardID(4);
    }

    @Override
    public void performForCard(DPlayer dPlayer) {
        if(dPlayer.getLocation() > 13){
            dPlayer.setTotalDiceValue(40 + 13 - dPlayer.getLocation() + dPlayer.getTotalDiceValue());
            dPlayer.setBalance(dPlayer.getBalance() + (int) DInstruction.getInstance().priceOfGoSquare);
        }else {
            dPlayer.setTotalDiceValue(13 - dPlayer.getLocation() + dPlayer.getTotalDiceValue());
        }
    }
}
