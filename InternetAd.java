/**
 * A class which extends Ad class to create InternetAd objects.
 */
public class InternetAd extends Ad {

    protected float pricePerDay;
    protected float autoDisplayPrice;
    protected float extraPagePrice;
    protected int autoDisplay;
    protected int extraPages;

    public InternetAd(String description, String agencyAFM, AdType adType, int productCode, int daysDisplayed,
            String reasoning, float pricePerDay, float autoDisplayPrice, float extraPagePrice, int autoDisplay,
            int extraPages) {
        super(description, agencyAFM, adType, productCode, daysDisplayed, reasoning);
        this.pricePerDay = pricePerDay;
        this.autoDisplayPrice = autoDisplayPrice;
        this.extraPagePrice = extraPagePrice;
        this.autoDisplay = autoDisplay;
        this.extraPages = extraPages;
    }

    /**
     * A method used if auto
     * display option is selected
     * 
     * @return autoDisplay
     */
    public boolean isAuto() {
        return autoDisplay == 1;
    }

    /**
     * A method that takes the
     * value of the following variables:
     * pricePerDay, extraPages, extraPagePrice
     * and autoDisplayPrice if it is activated
     * and based on that calculates the
     * total cost
     * 
     * @return totalCost
     */
    public float cost() {
        float totalCost = super.cost() * pricePerDay + extraPages * extraPagePrice;

        if (isAuto()) {
            totalCost += autoDisplayPrice;
        }

        return totalCost;
    }

    /**
     * A method which turns
     * the auto option into string
     * 
     * @return String
     */
    private String autoToString() {
        if (isAuto()) {
            return "Activated";
        }
        return "Not Activated";
    }

    /**
     * A method that uses the
     * value of the following variables:
     * pricePerDay, extraPages, extraPagePrice
     * and autoDisplayPrice if it is activated
     * to display cost as a string
     * 
     * @return value
     */
    public String costToString() {
        String value = super.costToString() + format(pricePerDay) + " + " + extraPages + " * " + format(extraPagePrice);

        if (isAuto()) {
            return value + " + " + format(autoDisplayPrice);
        }

        return value;
    }

    /**
     * A toString method which provides a
     * custom implementation to generate a
     * string representation of an InternetAd
     * object
     *
     * @return String
     */
    public String toString() {
        return super.toString()
                + "\nAuto Display: \t" + autoToString()
                + "\nExtra Pages: \t" + extraPages
                + String.format("\nPrice: \t\tPer Day: %s, Auto Display: %s, Per Extra Page: %s", format(pricePerDay), format(autoDisplayPrice), format(extraPagePrice));
    }

}