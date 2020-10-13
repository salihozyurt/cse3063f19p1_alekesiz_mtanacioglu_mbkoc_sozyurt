package BL;

import Controller.Main;
import DAL.DInstruction;
import DAL.DJSONReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BBoardTest {

    @Test
    @DisplayName("BBoard singleton safety check")
    void getInstance() {
        DJSONReader djsonReader = new DJSONReader(Main.INSTRUCTION_FILENAME);
        djsonReader.initInstructionObject();
        BBoard testInstance = BBoard.getInstance();
        assertEquals(testInstance, BBoard.getInstance());
    }
}