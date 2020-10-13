package BL.squares.communitychestcards;

import DAL.DPlayer;

public class BCommunityCardThirteen extends BCommunityCards{

    public BCommunityCardThirteen(){
        setCARD_INFO("Receive $25 consultancy fee.");
        setCardID(13);
    }

    @Override
    public void performForCard(DPlayer dPlayer) {
        dPlayer.setBalance(dPlayer.getBalance() + 25);
    }
}
