/**
 * A class which creates AdType objects
 */
public class AdType {
    static private int globalCode = 0;

    protected int code;
    protected String name;
    protected int type;
    protected String agency;

    public AdType(String name, int type, String agency) {
        globalCode++;
        this.code = globalCode;
        this.name = name;
        this.type = type;
        this.agency = agency;
    }

    /**
     * A method used to
     * check if the selected
     * ad type is "Printed"
     * 
     * @return type
     */
    public boolean isPrinted() {
        return type == 1;
    }

    /**
     * A method used to
     * check if the selected
     * ad type is "RadTv"
     * 
     * @return type
     */
    public boolean isRadTV() {
        return type == 2;
    }

    /**
     * A method used to
     * check if the selected
     * ad type is "Internet"
     * 
     * @return type
     */
    public boolean isInternetAd() {
        return type == 3;
    }

    /**
     * A method used to turn
     * the selected ad type into
     * string
     * 
     * @return String
     */
    private String typeToString() {
        if (isPrinted())
            return "Printed Ad";
        else if (isRadTV())
            return "Radio/Television Ad";
        else
            return "Internet Ad";
    }

    /**
     * A toString method which provides a
     * custom implementation to generate a
     * string representation of an AdType object
     *
     * @return String
     */
    public String toString() {
        return String.format("%s (%s)", name, typeToString());
    }
}
