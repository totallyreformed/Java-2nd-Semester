/**
 * A class which creates AdAgency objects
 */
public class AdAgency {

    protected String AFM;
    protected String name;

    public AdAgency(String AFM, String name) {
        this.AFM = AFM;
        this.name = name;
    }

    /**
     * A toString method which provides a
     *custom implementation to generate a
     *string representation of an AdAgency
     *object
     *
     * @return String
     */
    public String toString() {
        return "AFM: " + AFM + "\tName: " + name;
    }
}
