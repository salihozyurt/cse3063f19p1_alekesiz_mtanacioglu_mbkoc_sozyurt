package BL.squares.communitychestcards;

import BL.BMonopolyGame;
import BL.BPlayer;
import DAL.DPlayer;

import java.util.ArrayList;

public class BCommunityCardSeven extends BCommunityCards{
    private ArrayList<BPlayer> otherPlayer;
    private BMonopolyGame referanceToGetPlayers;
    private DPlayer tempDPlayer;

    public BCommunityCardSeven(){
        setCARD_INFO("Grand Opera Night. Collect $50 from every player for opening night seats. ");
        setCardID(7);
    }

    @Override
    public void performForCard(DPlayer dPlayer) {
        referanceToGetPlayers = new BMonopolyGame();
        otherPlayer = referanceToGetPlayers.getPlayers();
        int counter = referanceToGetPlayers.getPlayers().size();
        while (counter != 0){
            tempDPlayer = otherPlayer.get(counter).getDPlayer();
            if (tempDPlayer != dPlayer){
                dPlayer.setBalance(dPlayer.getBalance() + 50);
                tempDPlayer.setBalance(tempDPlayer.getBalance() - 50);
            }
            counter--;
        }
    }
}
