package BL;

import BL.squares.BRegularSquare;
import BL.squares.PropertyType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BRegularSquareTest {

    @Test
    void getSQUARE_TYPE() {
        BRegularSquare bRegularSquare = new BRegularSquare(PropertyType.NOCOLOR);
        assertEquals("REGULAR_SQUARE", bRegularSquare.getSQUARE_TYPE());
    }
}