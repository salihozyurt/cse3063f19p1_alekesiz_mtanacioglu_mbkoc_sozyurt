package BL.squares.communitychestcards;

import DAL.DPlayer;

public class BCommunityCardEigth extends BCommunityCards{

    public BCommunityCardEigth(){
        setCARD_INFO("Holiday Fund matures. Collect $100.");
        setCardID(8);
    }

    @Override
    public void performForCard(DPlayer dPlayer) {
        dPlayer.setBalance(dPlayer.getBalance() + 100);
    }
}
