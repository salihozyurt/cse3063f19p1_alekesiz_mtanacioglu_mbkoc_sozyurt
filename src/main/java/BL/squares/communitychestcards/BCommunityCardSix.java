package BL.squares.communitychestcards;

import DAL.DPlayer;

public class BCommunityCardSix extends BCommunityCards{

    public BCommunityCardSix(){
        setCARD_INFO("Go to Jail. Go directly to Jail. Do not pass GO, do not collect $200.");
        setCardID(6);
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
