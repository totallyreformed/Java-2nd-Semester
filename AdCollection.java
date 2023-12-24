import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * A collection for containing Advertisments.
 */
public class AdCollection extends Collection {
    AdCollection() {
        super();
        list = new ArrayList<Ad>();
    }

    /**
     * Return the Ad located at [index]
     * in the collection.
     * 
     * @param index
     * @return
     */
    public Ad get(int index) {
        return (Ad) super.get(index);
    }

    /**
     * Format a given float to 2 digits after pointer.
     * 
     * @param cost
     * @return String cost
     */
    static String format(float cost) {
        Locale locale = new Locale("en", "US");
        NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(locale);

        return dollarFormat.format(cost).toString();
    }

    /**
     * Displays all ads of the agency code provided.
     * 
     * @param agencyAFM
     */
    public void displayAgencyAds(String agencyAFM) {
        boolean exists = false;
        for (int i = 0; i < list.size(); i++) {
            final Ad ad = (Ad) list.get(i);
            if (!ad.agencyAFM.equals(agencyAFM)) {
                continue;
            }

            System.out.println("\n" + ad);
            exists = true;
        }
        if (!exists) {
            System.out.println("No ads found");
        }
    }

    /**
     * Displays the cost of each agency.
     * 
     * @param agencyAFM
     */
    public void displayAgencyCost(String agencyAFM) {
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            final Ad ad = (Ad) list.get(i);
            if (!ad.agencyAFM.equals(agencyAFM)) {
                continue;
            }

            System.out.printf("Ad Id Code: %s\tCost: %s\n", ad.code, ad.format(ad.cost()));

            sum += ad.cost();
        }

        System.out.println("--------------------------------");
        System.out.printf("Total Agency Cost: \033[1m%s\033[0m\n", format(sum));
    }

    /**
     * Calculates product costs according to the product code given.
     * 
     * @param code
     * @param show
     * @return cost
     */
    public float calculateProductCost(int code, boolean show) {
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            final Ad ad = (Ad) list.get(i);
            if (ad.productCode != code) {
                continue;
            }

            if (show) {
                System.out.printf("Ad Id Code: %s\tCost: %s\n", ad.code, ad.format(ad.cost()));
            }

            sum += ad.cost();
        }
        if (show) {
            System.out.println("--------------------------------");
            System.out.printf("Total Product Cost: \033[1m%s\033[0m\n", format(sum));
        }

        return sum;
    }

    /**
     * Displays product cost using the method above.
     * 
     * @param code
     */
    public float[] calculateAllProductCosts(int[] codes) {
        final float[] costs = new float[codes.length];

        for (int i = 0; i < codes.length; i++) {
            costs[i] = calculateProductCost(codes[i], false);
        }

        return costs;
    }

    /**
     * Display total cost for each and every on of the products.
     * 
     * @param codes
     */
    public void displayAllProductCosts(int[] codes) {
        float[] costs = calculateAllProductCosts(codes);
        sort(codes, costs);
        for (int i = 0; i < codes.length; i++) {
            System.out.println("Id Code: " + codes[i] + "\t Cost: " + format(costs[i]));
        }
    }

    /**
     * Calculate how many ads exist for products.
     * 
     * @param codes
     * @return
     */
    public int[] calculateAllProductAdsCount(int[] codes) {
        final int[] counts = new int[codes.length];

        for (int i = 0; i < codes.length; i++) {
            counts[i] = productAdsCount(codes[i]);
        }

        return counts;
    }

    /**
     * Display the number of available products using the method above
     * 
     * @param codes
     */
    public void displayAllProductAdsCount(int[] codes) {
        int[] counts = calculateAllProductAdsCount(codes);
        sort(codes, counts);
        for (int i = 0; i < codes.length; i++) {
            System.out.printf("Product Id Code: %s\tCount: %s\n", codes[i], counts[i]);
        }
    }

    /**
     * Get total count of ads for given product code.
     * 
     * @param code
     * @return (int) count
     */
    public int productAdsCount(int code) {
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            final Ad ad = (Ad) list.get(i);
            if (ad.productCode != code) {
                continue;
            }
            count++;
        }

        return count;
    }

    /**
     * Get list data per line for saving purposes.
     * 
     * @return
     */
    public String[][] data() {
        String[][] data = new String[list.size()][13];

        Ad adv;
        for (int i = 0; i < list.size(); i++) {
            adv = get(i);

            data[i][0] = "TYPE " + adv.adType.type;
            data[i][1] = "DESCR " + formatS(adv.description);
            data[i][2] = "AGENT_AFM " + formatS(adv.agencyAFM);
            data[i][3] = "ADVTYPE_CODE " + (adv.adType.code - 1);
            data[i][4] = "ITEM_CODE " + (adv.productCode - 1);
            data[i][5] = "DURATION " + adv.daysDisplayed;

            if (adv instanceof PrintedAd) {
                data[i][6] = "WORDS " + ((PrintedAd) adv).wordCount;
                data[i][7] = "PAGE " + ((PrintedAd) adv).pageSelection;
                data[i][8] = "FIRST_PRICE " + ((PrintedAd) adv).firstPagePrice;
                data[i][9] = "MIDDLE_PRICE " + ((PrintedAd) adv).middlePagePrice;
                data[i][10] = "LAST_PRICE " + ((PrintedAd) adv).lastPagePrice;
                data[i][11] = "JUSTIFICATION " + formatS(adv.reasoning);
            }

            else if (adv instanceof RadTVAd) {
                data[i][6] = "DUR_SEC " + ((RadTVAd) adv).durationInSec;
                data[i][7] = "TIME " + ((RadTVAd) adv).timeSelection;
                data[i][8] = "MORN_PRICE " + ((RadTVAd) adv).morningPrice;
                data[i][9] = "NOON_PRICE " + ((RadTVAd) adv).noonPrice;
                data[i][10] = "AFNOON_PRICE " + ((RadTVAd) adv).afternoonPrice;
                data[i][11] = "NIGHT_PRICE " + ((RadTVAd) adv).nightPrice;
                data[i][12] = "JUSTIFICATION " + formatS(adv.reasoning);
            }

            else if (adv instanceof InternetAd) {
                data[i][6] = "AUTO_ON " + ((InternetAd) adv).autoDisplay;
                data[i][7] = "EXTRA_PAGES " + ((InternetAd) adv).extraPages;
                data[i][8] = "PERDAY_PRICE " + ((InternetAd) adv).pricePerDay;
                data[i][9] = "AUTO_PRICE " + ((InternetAd) adv).autoDisplayPrice;
                data[i][10] = "EXTRAPAGE_PRICE " + ((InternetAd) adv).extraPagePrice;
                data[i][11] = "JUSTIFICATION " + formatS(adv.reasoning);
            }
        }

        return data;
    }
}
