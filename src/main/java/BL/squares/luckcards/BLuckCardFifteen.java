package BL.squares.luckcards;

import BL.BMonopolyGame;
import BL.BPlayer;
import DAL.DInstruction;
import DAL.DPlayer;

import java.util.ArrayList;

public class BLuckCardFifteen extends BLuckCards {
    private ArrayList<BPlayer> otherPlayer;
    private BMonopolyGame referanceToGetPlayers;
    private DPlayer tempDPlayer;

    public BLuckCardFifteen() {
        setCARD_INFO("You have been elected Chairman of the Board. Pay each player $50. ");
        setCardID(15);
    }

    @Override
    public void performForCard(DPlayer dPlayer) {
        referanceToGetPlayers = new BMonopolyGame();
        otherPlayer = referanceToGetPlayers.getPlayers();
        int counter = referanceToGetPlayers.getPlayers().size();
        while (counter != 0){
            tempDPlayer = otherPlayer.get(counter).getDPlayer();
            if (tempDPlayer != dPlayer){
                dPlayer.setBalance(dPlayer.getBalance() - 50);
                tempDPlayer.setBalance(tempDPlayer.getBalance() + 50);
            }
            counter--;
        }

    }
}
