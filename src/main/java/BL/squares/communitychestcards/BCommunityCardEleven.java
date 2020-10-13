package BL.squares.communitychestcards;

import DAL.DPlayer;

public class BCommunityCardEleven extends BCommunityCards{

    public BCommunityCardEleven(){
        setCARD_INFO("Hospital Fees. Pay $50. ");
        setCardID(11);
    }

    @Override
    public void performForCard(DPlayer dPlayer) {
        dPlayer.setBalance(dPlayer.getBalance() - 50);
    }
}
