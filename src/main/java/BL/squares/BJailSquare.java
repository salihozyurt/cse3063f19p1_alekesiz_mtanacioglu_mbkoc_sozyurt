package BL.squares;

import BL.squares.luckcards.BLuckCardEigth;
import DAL.DInstruction;
import DAL.DPlayer;

import java.util.Arrays;
import java.util.HashMap;

/**
 * <h>BJailSquare</h>
 *
 * <p>A class for Jail Component in Monopoly game.</p>
 *
 * @author Muhammed Bera Koç
 * @version 1.1
 */
public class BJailSquare extends BSquare {

    private HashMap<DPlayer, Integer> jailRecords;

    public BJailSquare(PropertyType type) {
        jailRecords = new HashMap<>();
        this.pType=type;
    }

    /**
     *
     * <p>When player reaches the Jail square this method will be triggered. It adds player to jailRecords
     * and marks player as arrested.</p>
     *
     * @param player
     * @return void
     */
    @Override
    public void performOnLand(DPlayer player) {
        if (player == new BLuckCardEigth().getCardOwner()) return;
        jailRecords.put(player, 0);
        player.setArrested(true);
    }

    /**
     * <p>In each tour scans the jail records to conduct appropriate actions for those who are arrested.</p>
     */
    public void scanPlayerRecord(DPlayer currentPlayer) {
        int stayCount = jailRecords.get(currentPlayer);
        if (stayCount < 3) {
            int[] currentDiceValues = currentPlayer.getPlayerDice().rollDiceWithoutConstraint();
            if (currentDiceValues[0] == currentDiceValues[1]) {
                currentPlayer.setArrested(false);
                jailRecords.remove(currentPlayer);
                return;
            }
            jailRecords.put(currentPlayer, ++stayCount);
        } else {
            currentPlayer.setBalance(currentPlayer.getBalance() - (int) DInstruction.getInstance().priceOfJailSquare);
            currentPlayer.setArrested(false);
            jailRecords.remove(currentPlayer);
        }
    }

    @Override
    public String getSQUARE_TYPE() {
        return "JAIL_SQUARE";
    }

    public HashMap<DPlayer, Integer> getJailRecords() {
        return jailRecords;
    }
}
