package BL.squares.luckcards;

import DAL.DPlayer;

public class BLuckCardFourteen extends BLuckCards {

    public BLuckCardFourteen() {
        setCARD_INFO("Take a walk on the Ginza. ");
        setCardID(14);
    }

    @Override
    public void performForCard(DPlayer dPlayer) {
        if(dPlayer.getLocation() > 8){
            dPlayer.setTotalDiceValue(dPlayer.getTotalDiceValue() - (dPlayer.getLocation() - 8));
        }else {
            dPlayer.setTotalDiceValue(8 - dPlayer.getLocation() + dPlayer.getTotalDiceValue());
        }
    }
}
