package BL;

import BL.squares.BTaxSquare;
import BL.squares.PropertyType;
import Controller.Main;
import DAL.DInstruction;
import DAL.DJSONReader;
import DAL.DPiece;
import DAL.DPlayer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BTaxSquareTest {

    private static DPlayer dPlayer;

    @BeforeAll
    static void beforeAllTestMethod() {
        dPlayer = new DPlayer(DPiece.PieceType.values()[(int) (Math.random() * 8)], 1000);
    }

    @Test
    void performOnLand() {
        DJSONReader djsonReader = new DJSONReader(Main.INSTRUCTION_FILENAME);
        djsonReader.initInstructionObject();
        DInstruction instruction = DInstruction.getInstance();
        BTaxSquare bTaxSquare = new BTaxSquare(PropertyType.NOCOLOR);
        bTaxSquare.performOnLand(dPlayer);
        assertEquals(1000 - instruction.priceOfTaxSquares, dPlayer.getBalance());
    }

    @Test
    void getSQUARE_TYPE() {
        BTaxSquare bTaxSquare = new BTaxSquare(PropertyType.NOCOLOR);
        assertEquals("TAX_SQUARE", bTaxSquare.getSQUARE_TYPE());
    }

}