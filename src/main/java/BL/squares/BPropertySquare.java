package BL.squares;

import BL.BPlayer;
import BL.BTerminal;
import DAL.DPlayer;

/**
 * <h>BPropertySquare</h>
 *
 * <p>We extend the class from BSquare to decide square type.</p>
 *
 * @author Salih ÖZYURT
 * @version 1.0
 */

public class BPropertySquare extends BSquare {

    private final String SQUARE_TYPE = "PROPERTY_SQUARE";
    private BTerminal bTerminal = new BTerminal();
    private boolean hasHouse;
    private boolean hasHotel;

    /**
     * <p>Constructor takes number of arguments and assigns it to the object.</p>
     *
     * @param name
     * @param type
     * @param rent
     * @param price
     */
    public BPropertySquare(String name, PropertyType type, int rent, int price){
        this.pType = type;
        this.name = name;
        this.rent = rent;
        this.price = price;
        this.hasHouse = false;
        this.hasHotel = false;
    }

    @Override
    public void performOnLand(DPlayer player) {
        if(this.getOwnerOfSquare() != null){
            getRent(this.getOwnerOfSquare().getDPlayer(), player);
        }
    }

    public void getRent(DPlayer owner, DPlayer renter){
        if (renter == owner) return;
        String pieceType = renter.getPieceType().toString();
        bTerminal.printRentProcess(pieceType , this);
        renter.setBalance(renter.getBalance() - this.rent);
        owner.setBalance(owner.getBalance() + this.rent);
    }

    public String getName() {
        return name;
    }

    @Override
    public String getSQUARE_TYPE() {
        return this.SQUARE_TYPE;
    }

    public boolean getHasHouse() {
        return hasHouse;
    }

    public void setHasHouse(boolean hasHouse) {
        this.hasHouse = hasHouse;
    }

    public boolean getHasHotel() {
        return hasHotel;
    }

    public void setHasHotel(boolean hasHotel) {
        this.hasHotel = hasHotel;
    }

    /**
     * <p>We decide to build hotel if  player has the square already.</p>
     *
     * @return boolean
     */
    public void buildHouse(){
        setHasHouse(true);
        this.price = getHousePrice() + this.price;
        this.rent*=2;
    }

    /**
     * <p>We decide to build hotel if  player has a house in same square already.</p>
     *
     * @return boolean
     */
    public boolean buildHotel(){
        if (getHasHouse()){
            setHasHotel(true);
            this.price = getHotelPrice() + this.price;
            this.rent*=2;
            return true;
        }
        else
        {
            setHasHotel(false);
            return false;
        }
    }

    public int getHousePrice(){
        return getPrice()*2;
    }

    public int getHotelPrice(){
        return getPrice()*2;
    }
}
