package BL.squares.luckcards;

import DAL.DInstruction;
import DAL.DPlayer;

public class BLuckCardThirteen extends BLuckCards {

    public BLuckCardThirteen() {
        setCARD_INFO("Take a trip to Embassy Suites. If you pass Go, collect $200. ");
        setCardID(13);
    }

    @Override
    public void performForCard(DPlayer dPlayer) {
        if(dPlayer.getLocation() > 16){
            dPlayer.setTotalDiceValue(40 + 16 - dPlayer.getLocation() + dPlayer.getTotalDiceValue());
            dPlayer.setBalance(dPlayer.getBalance() + (int) DInstruction.getInstance().priceOfGoSquare);
        }else {
            dPlayer.setTotalDiceValue(16 - dPlayer.getLocation() + dPlayer.getTotalDiceValue());
        }
    }
}
