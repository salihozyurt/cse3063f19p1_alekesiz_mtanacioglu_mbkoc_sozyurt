package BL.squares.luckcards;

import DAL.DPlayer;

public class BLuckCardTen extends BLuckCards {

    public BLuckCardTen() {
        setCARD_INFO("Go to Jail. Go directly to Jail. Do not pass GO, do not collect $200. ");
        setCardID(10);
    }

    @Override
    public void performForCard(DPlayer dPlayer) {
        if(dPlayer.getLocation() > 10){
            dPlayer.setTotalDiceValue(dPlayer.getTotalDiceValue() - (dPlayer.getLocation() - 10));
        }else {
            dPlayer.setTotalDiceValue(10 - dPlayer.getLocation() + dPlayer.getTotalDiceValue());
        }
    }
}
