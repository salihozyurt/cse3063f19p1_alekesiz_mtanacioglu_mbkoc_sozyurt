package BL.squares.communitychestcards;

import DAL.DPlayer;

public class BCommunityCardNine extends BCommunityCards{

    public BCommunityCardNine(){
        setCARD_INFO("Income tax refund. Collect $20.");
        setCardID(9);
    }

    @Override
    public void performForCard(DPlayer dPlayer) {
        dPlayer.setBalance(dPlayer.getBalance() + 20);
    }
}
