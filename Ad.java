import java.text.NumberFormat;
import java.util.Locale;

/**
 * A class which creates Ad objects
 */
public class Ad {
    static private int globalCode = 0;

    protected int code;
    protected String description;
    protected String agencyAFM;
    protected AdType adType;
    protected int productCode;
    protected int daysDisplayed;
    protected String reasoning;

    public Ad(String description, String agencyAFM, AdType adType, int productCode, int daysDisplayed,
            String reasoning) {
        globalCode++;

        this.code = globalCode;
        this.description = description;
        this.agencyAFM = agencyAFM;
        this.adType = adType;
        this.productCode = productCode;
        this.daysDisplayed = daysDisplayed;
        this.reasoning = reasoning;
    }

    /**
     * A method that returns
     * the days that the ad will
     * be displayed
     * 
     * @return daysDisplayed
     */
    public float cost() {
        return daysDisplayed;
    }

    /**
     * The method ensures that the floating-point
     * value is formatted consistently
     * with two decimal places and a dollar sign.
     * 
     * @param value
     * @return String
     */
    public String format(float value) {
        Locale locale = new Locale("en", "US");
        NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(locale);

        return dollarFormat.format(value).toString();
    }

    /**
     * A method which uses value
     * of the daysDisplayed variable
     * to display cost as a string
     * 
     * @return String
     */
    public String costToString() {
        return daysDisplayed + " * ";
    }

    /**
     * A toString method which provides a
     * custom implementation to generate a
     * string representation of an Ad object
     *
     * @return String
     */
    public String toString() {
        return "\033[1mId Code: \t" + code + "\033[0m"
                + "\nDescription: \t" + description
                + "\nAd Type: \t" + adType
                + "\nAgency AFM: \t" + agencyAFM
                + "\nProduct Code: \t" + productCode
                + "\nDays Displayed: " + daysDisplayed
                + "\nCost: \t\t" + costToString() + " = " + format(cost())
                + "\nReasoning: \t" + reasoning;
    }
}
