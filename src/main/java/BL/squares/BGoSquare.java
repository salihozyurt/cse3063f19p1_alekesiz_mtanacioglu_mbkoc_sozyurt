package BL.squares;

import DAL.DPlayer;
import DAL.DInstruction;
/***
 *BGoSquare is class that represents starting point of game in Business Layer.
 * It changes balance.
 *
 * @author Mücahit Tanacıoğlu
 * @version 1.0
 * @since 11.
 */
public class BGoSquare extends BSquare {
    private final String SQUARE_TYPE = "GO_SQUARE";
    private final DInstruction DInstructionInstance = DInstruction.getInstance();
    public BGoSquare(PropertyType type){this.pType=type;}
    /**
     *<p>Perform on land function inherited from BSquare which is generic type for squares.
     * For Go square its add amount of priceOfGoSquare to players balance </p>
     * <p>This method runs when player pass the go square.</p>
     * @param player A DPlayer object which is contain data for player.
     * @return void
     */
    @Override
    public void performOnLand(DPlayer player) {
        player.setBalance(player.getBalance() + (int) DInstructionInstance.priceOfGoSquare);
    }
    /**
     * <p>Another method inherited from Bsquare<p/>
     * <p>This method returns type of square .</p>
     * @return String
     */
    @Override
    public String getSQUARE_TYPE() {
        return this.SQUARE_TYPE;
    }
}
