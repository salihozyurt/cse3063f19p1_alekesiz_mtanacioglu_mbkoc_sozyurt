package DAL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

/**
 * A class to pull data from a JSON File. Although it was created to get any info in JSON
 * after a while it was specialised to obtain data from game.json file.
 * @auther Muhammed Bera Koç
 * @since 1.0
 */
public class DJSONReader {

    private JSONObject currentJSONObject;

    /**
     * Creates a JSONObject using the given file name.
     *
     * @param JSONFileName
     */
    public DJSONReader(String JSONFileName) {
        try {
            currentJSONObject = (JSONObject) new JSONParser().parse(new FileReader(JSONFileName));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * <p>Gets any info related to the given key returning it in Object class form.</p>
     *
     * @param key
     * @return Object
     */
    public Object getValueUsingKey(String key) {
        return currentJSONObject.get(key);
    }

    /**
     * Initialises DInstruction instance using given JSON File.
     *
     * @return void
     */
    public void initInstructionObject() {
        DInstruction.getInstance(
                (Long) getValueUsingKey("countOfPlayers"),
                (Long) getValueUsingKey("startMoney"),
                (Long) getValueUsingKey("priceOfTaxSquare"),
                (Long) getValueUsingKey("priceOfGoSquare"),
                (Long) getValueUsingKey("priceOfJailSquare"),
                (String) getValueUsingKey("mapFilename")
        );
    }
}
