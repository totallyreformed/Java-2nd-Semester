/**
 * A class which extends Ad class to create RadTVAd objects.
 */
public class RadTVAd extends Ad {

    protected float morningPrice;
    protected float noonPrice;
    protected float afternoonPrice;
    protected float nightPrice;
    protected int durationInSec;
    protected int timeSelection;

    public RadTVAd(String description, String agencyAFM, AdType adType, int productCode, int daysDisplayed,
            String reasoning, float morningPrice, float noonPrice, float afternoonPrice, float nightPrice,
            int durationInSec, int timeSelection) {

        super(description, agencyAFM, adType, productCode, daysDisplayed, reasoning);
        this.morningPrice = morningPrice;
        this.noonPrice = noonPrice;
        this.afternoonPrice = afternoonPrice;
        this.nightPrice = nightPrice;
        this.durationInSec = durationInSec;
        this.timeSelection = timeSelection;
    }

    /**
     * A method that takes the
     * value of the timeSelection variable
     * and based on that calculates the
     * total cost
     * 
     * @return float
     */
    public float cost() {
        float totalCost = 0;
        if (timeSelection == 1) {
            totalCost = morningPrice * durationInSec;
        } else if (timeSelection == 2) {
            totalCost = noonPrice * durationInSec;
        } else if (timeSelection == 3) {
            totalCost = afternoonPrice * durationInSec;
        } else if (timeSelection == 4) {
            totalCost = nightPrice * durationInSec;
        }
        return super.cost() * totalCost;
    }

    /**
     * A method that uses the
     * value of the timeSelection variable
     * to display cost as a string
     * 
     * @return value
     */
    public String costToString() {
        String value = super.costToString() + durationInSec + " * ";
        if (timeSelection == 1) {
            value += format(morningPrice);
        } else if (timeSelection == 2) {
            value += format(noonPrice);
        } else if (timeSelection == 3) {
            value += format(afternoonPrice);
        } else if (timeSelection == 4) {
            value += format(nightPrice);
        }
        return value;
    }

    /**
     * A method that uses the
     * value of the timeSelection variable
     * 
     * @return string
     */
    public String timeSelectionToString() {
        switch (timeSelection) {
            case 1:
                return "Morning";
            case 2:
                return "Noon";
            case 3:
                return "Afternoon";
            case 4:
                return "Night";
            default:
                return "Error";
        }
    }

    /**
     * A toString method which provides a
     * custom implementation to generate a
     * string representation of a RadTVAd
     * object
     *
     * @return String
     */
    public String toString() {
        return super.toString()
                + "\nDuration (sec): " + durationInSec
                + "\nTime Selection: " + timeSelectionToString()
                + String.format("\nPrice: \t\tMorning: %s, Noon: %s, Afternoon: %s, Night: %s", format(morningPrice), format(noonPrice), format(afternoonPrice), format(nightPrice));
    }

}
