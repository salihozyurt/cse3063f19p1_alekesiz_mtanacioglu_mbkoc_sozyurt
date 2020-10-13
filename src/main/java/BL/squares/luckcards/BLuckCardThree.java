package BL.squares.luckcards;

import DAL.DInstruction;
import DAL.DPlayer;

public class BLuckCardThree extends BLuckCards{

    public BLuckCardThree() {
        setCARD_INFO("Advance to Fairmont. If you pass Go, collect $200. ");
        setCardID(3);
    }

    @Override
    public void performForCard(DPlayer dPlayer) {
        if(dPlayer.getLocation() > 19){
            dPlayer.setTotalDiceValue(40 + 19 - dPlayer.getLocation() + dPlayer.getTotalDiceValue());
            dPlayer.setBalance(dPlayer.getBalance() + (int) DInstruction.getInstance().priceOfGoSquare);
        }else {
            dPlayer.setTotalDiceValue(19 - dPlayer.getLocation() + dPlayer.getTotalDiceValue());
        }
    }
}
