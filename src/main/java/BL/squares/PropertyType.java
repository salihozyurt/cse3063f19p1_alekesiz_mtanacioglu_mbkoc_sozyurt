package BL.squares;
public enum PropertyType {
    BROWN(0),
    TURQUOISE(1),
    PURPLE(2),
    ORANGE(3),
    RED(4),
    YELLOW(5),
    GREEN(6),
    BLUE(7),
    NOCOLOR(8);

    private int value;


    PropertyType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}