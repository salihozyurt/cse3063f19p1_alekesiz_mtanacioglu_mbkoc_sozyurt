package DAL;

/**
 * DPiece class contains PieceType enum to use it in DPlayer Class.
 * @author Muhammed Bera Koç
 * @version 1.0
 */
public class DPiece {

    /**
     * PieceType is an enum which contains eight different value.
     */
    public enum PieceType {
        DOG(0),
        TOP_HAT(1),
        BOOT(2),
        CAT(3),
        WHEELBARROW(4),
        RACING_CAR(5),
        BATTLESHIP(6),
        THIMBLE(7);

        private int value;

        /**
         * Assigns the specific value for a PieceType area.
         *
         * @param value
         */
        PieceType(int value) {
            this.value = value;
        }

        /**
         * Returns the integer value of an PieceType area.
         *
         * @return int
         */
        public int getValue() {
            return value;
        }
    }
}
