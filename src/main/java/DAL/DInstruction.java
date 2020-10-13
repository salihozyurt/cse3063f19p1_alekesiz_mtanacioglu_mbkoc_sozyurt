package DAL;

import Controller.Main;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * DInstruction Singleton class represents a data block for instructions of the game given in JSON File.
 * @author Muhammed Bera Koç
 * @version 1.0
 */
public class DInstruction {

    public long countOfPlayers, startMoney, priceOfTaxSquares, priceOfGoSquare, priceOfJailSquare;
    public String mapFilename;
    public ArrayList[] places;

    private static DInstruction instance;

    /**
     * <p>Constructor takes number of arguments and assigns it to the object.</p>
     *
     * @param countOfPlayers
     * @param startMoney
     * @param priceOfTaxSquares
     * @param priceOfGoSquare
     * @param priceOfJailSquare
     * @param mapFilename
     */
    private DInstruction(long countOfPlayers, long startMoney, long priceOfTaxSquares,
                         long priceOfGoSquare, long priceOfJailSquare, String mapFilename) {
        this.countOfPlayers = countOfPlayers;
        this.startMoney = startMoney;
        this.priceOfTaxSquares = priceOfTaxSquares;
        this.priceOfGoSquare = priceOfGoSquare;
        this.priceOfJailSquare = priceOfJailSquare;
        this.mapFilename = mapFilename;
        JSONObject mapObject = null;
        try {
            mapObject = (JSONObject) new JSONParser().parse(new FileReader(mapFilename));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        places = new ArrayList[Main.PLACE_NUMBER];
        for (int i = 0; i < Main.PLACE_NUMBER; ++i) {
            places[i] = new ArrayList<String>();
            assert mapObject != null;
            JSONArray currentPlaceArray = (JSONArray) mapObject.get(String.valueOf(i));
            for (Object node: currentPlaceArray) {
                places[i].add(node);
            }
        }
    }

    /**
     * Method to obtain Singleton object.
     *
     * @return DInstruction
     */
    public static DInstruction getInstance() {
        return instance;
    }

    public static DInstruction getInstance(long countOfPlayers, long startMoney,
                                           long priceOfTaxSquares, long priceOfGoSquare, long priceOfJailSquare,
                                           String mapFilename) {
        if (instance == null) {
            instance = new DInstruction(countOfPlayers, startMoney,
                    priceOfTaxSquares, priceOfGoSquare, priceOfJailSquare, mapFilename);
        }
        return instance;
    }

    @Override
    public String toString() {
        return "countOfPlayers: " + countOfPlayers + "\nstartMoney: " + startMoney
                + "\npriceOfTaxSquares: " + priceOfTaxSquares + "\npriceOfGoSquare: " + priceOfGoSquare
                + "\npriceOfJailSquare: " + priceOfJailSquare + "\nmapFilename: " + mapFilename;
    }
}
