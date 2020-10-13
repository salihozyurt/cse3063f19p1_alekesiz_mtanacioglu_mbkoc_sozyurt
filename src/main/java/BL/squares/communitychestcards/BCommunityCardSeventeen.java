package BL.squares.communitychestcards;

import BL.BMonopolyGame;
import BL.BPlayer;
import DAL.DPlayer;

import java.util.ArrayList;

public class BCommunityCardSeventeen extends BCommunityCards{
    private ArrayList<BPlayer> otherPlayer;
    private BMonopolyGame referanceToGetPlayers;
    private DPlayer tempDPlayer;

    public BCommunityCardSeventeen(){
        setCARD_INFO("It is your birthday. Collect $10 from every player.");
        setCardID(17);
    }

    @Override
    public void performForCard(DPlayer dPlayer) {
        referanceToGetPlayers = new BMonopolyGame();
        otherPlayer = referanceToGetPlayers.getPlayers();
        int counter = referanceToGetPlayers.getPlayers().size();
        while (counter != 0){
            tempDPlayer = otherPlayer.get(counter).getDPlayer();
            if (tempDPlayer != dPlayer){
                dPlayer.setBalance(dPlayer.getBalance() + 10);
                tempDPlayer.setBalance(tempDPlayer.getBalance() - 10);
            }
            counter--;
        }
    }
}
