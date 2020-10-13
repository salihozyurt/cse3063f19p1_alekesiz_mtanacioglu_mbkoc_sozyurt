package DAL;

import Controller.Main;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DJSONReaderTest {

    @Test
    void getValueUsingKey() {
        DJSONReader djsonReader = new DJSONReader("test.json");
        assertEquals("John Carpenter", djsonReader.getValueUsingKey("username"));
        assertEquals(35, (Long) djsonReader.getValueUsingKey("age"));
    }
}