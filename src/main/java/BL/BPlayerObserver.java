package BL;

import BL.squares.BSquare;

/***
 *BPlayerObserver interface to make actions on players.
 *
 * @author Mücahit Tanacıoğlu
 * @version 1.0
 * @since 11.
 */

public interface BPlayerObserver {

    /**
     * @param currentDiceValue
     * @param currentSquare
     * @return void
     */

    void checkAndUpdatePlayer(int currentDiceValue, BSquare currentSquare);
}
