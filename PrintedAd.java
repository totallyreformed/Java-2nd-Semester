/**
 * A class which extends Ad class to create PrintedAd objects.
 */
public class PrintedAd extends Ad {

    protected float firstPagePrice;
    protected float middlePagePrice;
    protected float lastPagePrice;
    protected int pageSelection;
    protected int wordCount;

    public PrintedAd(String description, String agencyAFM, AdType adType, int productCode, int daysDisplayed,
            String reasoning, float firstPagePrice, float middlePagePrice, float lastPagePrice, int wordCount,
            int pageSelection) {
        super(description, agencyAFM, adType, productCode, daysDisplayed, reasoning);

        this.firstPagePrice = firstPagePrice;
        this.middlePagePrice = middlePagePrice;
        this.lastPagePrice = lastPagePrice;
        this.wordCount = wordCount;
        this.pageSelection = pageSelection;
    }

    /**
     * A method that takes the
     * value of the pageSelection variable
     * and based on that and the price
     * of the selected time zone calculates the
     * total cost
     * 
     * @return float
     */
    public float cost() {
        float totalCost = 0;
        if (pageSelection == 1) {
            totalCost = wordCount * firstPagePrice;
        } else if (pageSelection == 2) {
            totalCost = wordCount * middlePagePrice;
        } else {
            totalCost = wordCount * lastPagePrice;
        }
        return super.cost() * totalCost;
    }

    /**
     * A method that uses the
     * value of the pageSelection variable
     * to display cost as a string
     * 
     * @return String
     */
    public String costToString() {
        String value = super.costToString() + wordCount + " * ";
        if (pageSelection == 1) {
            return value += format(firstPagePrice);
        } else if (pageSelection == 2) {
            return value += format(middlePagePrice);
        } else {
            return value += format(lastPagePrice);
        }
    }

    /**
     * A method that uses the
     * value of the pageSelection variable
     * 
     * @return String
     */
    public String pageSelectionToString() {
        switch (pageSelection) {
            case 1:
                return "First Page";
            case 2:
                return "Middle Page";
            case 3:
                return "Last Page";

            default:
                return "Error";
        }
    }

    /**
     * A toString method which provides a
     * custom implementation to generate a
     * string representation of a PrintedAd object
     *
     * @return String
     */
    public String toString() {
        return super.toString()
                + "\nWord Count: \t" + wordCount
                + "\nPage Selection: " + pageSelectionToString()
                + String.format("\nPrice: \t\tFirst Page: %s, Middle Page: %s, Last Page: %s", format(firstPagePrice), format(middlePagePrice), format(lastPagePrice));
    }

}
