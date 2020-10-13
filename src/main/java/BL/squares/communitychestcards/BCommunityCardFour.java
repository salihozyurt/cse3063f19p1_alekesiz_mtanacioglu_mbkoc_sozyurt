package BL.squares.communitychestcards;

import DAL.DPlayer;

public class BCommunityCardFour extends BCommunityCards{

    public BCommunityCardFour(){
        setCARD_INFO("From sale of stock you get $50");
        setCardID(4);
    }

    @Override
    public void performForCard(DPlayer dPlayer) {
        dPlayer.setBalance(dPlayer.getBalance() + 50);

    }
}
