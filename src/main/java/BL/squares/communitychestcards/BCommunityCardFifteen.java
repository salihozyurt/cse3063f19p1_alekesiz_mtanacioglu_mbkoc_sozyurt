package BL.squares.communitychestcards;

import DAL.DPlayer;

public class BCommunityCardFifteen extends BCommunityCards{

    public BCommunityCardFifteen(){
        setCARD_INFO("You have won second prize in a beauty contest. Collect $10.");
        setCardID(15);
    }

    @Override
    public void performForCard(DPlayer dPlayer) {
        dPlayer.setBalance(dPlayer.getBalance() + 10);
    }
}
