package BL.squares.communitychestcards;

import DAL.DPlayer;

public class BCommunityCardTwelve extends BCommunityCards{

    public BCommunityCardTwelve(){
        setCARD_INFO("School fees. Pay $50.");
        setCardID(12);
    }

    @Override
    public void performForCard(DPlayer dPlayer) {
        dPlayer.setBalance(dPlayer.getBalance() - 50);
    }
}
