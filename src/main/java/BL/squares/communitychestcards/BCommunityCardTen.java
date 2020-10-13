package BL.squares.communitychestcards;

import DAL.DPlayer;

public class BCommunityCardTen extends BCommunityCards{

    public BCommunityCardTen(){
        setCARD_INFO("Life insurance matures. Collect $100");
        setCardID(10);
    }

    @Override
    public void performForCard(DPlayer dPlayer) {
        dPlayer.setBalance(dPlayer.getBalance() + 100);
    }
}
