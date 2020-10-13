package spring.consumer;

import BL.squares.BPropertySquare;
import DAL.DPlayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import BL.squares.BSquare;
import spring.log.Logger;

/**
 * A common Consumer class for objects in type of BSquare.
 * BSquare is an abstract method and it has numerous
 * offspring designed for various functionality.
 *
 * <p>
 *     It has one wrapped method which invokes logger and
 *     trigger performOnLand method.
 *
 * <p>
 *     The class is a component and it is method-autowired.
 *     It has only one variable which is square.
 *
 * @author Muhammed Bera KOÇ
 * @since 1.3
 */
@Component
public class SquareConsumer {

    private BSquare square;

    /**
     * Autowired setter method for Spring framework
     *
     * @param square A square which is polymorphic for BSquare
     */
    @Autowired
    public void setSquare(BSquare square) {
        this.square = square;
    }

    /**
     * A encapsulated method for performOnLand method in BSquare.
     * It cooperates with Logger class to save the current statistics
     * to app.log file. It has two logging attempt one for the start
     * and one for the end.
     *
     * @param dPlayer Current player which has trodden upon a Square
     */
    public void conductAction(DPlayer dPlayer) {
        Logger.instance.LOGGER.log(Logger.DEFAULT_LEVEL, String.format(
                "Before: Name: [%s], Balance: [%d], Turn: [%d], Square: [%s], Square Name: [%s]", dPlayer.getPieceType(),
                dPlayer.getBalance(), dPlayer.roundCounter, square.getSQUARE_TYPE() ,square.name
        ));
        square.performOnLand(dPlayer);
        Logger.instance.LOGGER.log(Logger.DEFAULT_LEVEL, String.format(
                "After: Name: [%s], Balance: [%d], Turn: [%d]", dPlayer.getPieceType(), dPlayer.getBalance(),
                dPlayer.roundCounter
        ));
    }
}
