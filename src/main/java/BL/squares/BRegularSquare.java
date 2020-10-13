package BL.squares;

import DAL.DPlayer;

/**
 * <h>BRegularSquare</h>
 *
 * <p>We extend the class from BSquare to decide square type.</p>
 *
 * @author Salih ÖZYURT
 * @version 1.0
 */
public class BRegularSquare extends BSquare {

    public BRegularSquare(PropertyType type){
        this.pType = type;
    }

    private final String SQUARE_TYPE = "REGULAR_SQUARE";

    /**
     * <p>The method is empty, but it will be fill when regular square is clear type such as Texas, New York.</p>
     * <p>We will use this method to decide what happen for regular square type.</p>
     *
     * @param player We will use the parameter to reach current Player.
     * @return void
     */
    @Override
    public void performOnLand(DPlayer player) {

    }

    /**
     * <p>The method is getter method to square type.</p>
     *
     * @return void
     */
    @Override
    public String getSQUARE_TYPE() {
        return this.SQUARE_TYPE;
    }
}
