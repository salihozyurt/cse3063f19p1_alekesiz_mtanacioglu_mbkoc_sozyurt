package BL.squares.communitychestcards;

import DAL.DInstruction;
import DAL.DPlayer;

public class BCommunityCardTwo extends BCommunityCards {

    public BCommunityCardTwo(){
        setCARD_INFO("Bank error in your favor. Collect $200.");
        setCardID(2);
    }

    @Override
    public void performForCard(DPlayer dPlayer) {
        dPlayer.setBalance(dPlayer.getBalance() + 200);
    }
}
