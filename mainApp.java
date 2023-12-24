
/**
 * Team Number: 070
 * 
 * @author Alexandros Bogdos - p3220134
 * @author Maria Stamadianou - p3220194
 * @author Napoleon Charalampidis - p3220225
 */

import java.util.Scanner;

public class mainApp {
    /**
     * Print the prompt and read and return
     * the user's input using the given scanner.
     * 
     * @param scanner
     * @param prompt
     * @return String
     */
    static String read(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    /**
     * Return as Integer the result from read().
     * 
     * @param scanner
     * @param prompt
     * @return Integer
     */
    static int readInt(Scanner scanner, String prompt) {
        return Integer.parseInt(read(scanner, prompt));
    }

    /**
     * Return as Float the result from read().
     * 
     * @param scanner
     * @param prompt
     * @return Integer
     */
    static float readFloat(Scanner scanner, String prompt) {
        return Float.parseFloat(read(scanner, prompt));
    }

    /**
     * Display to the user the application menu.
     * Return his option selection.
     * 
     * @param scanner
     * @return String
     */
    static String displayMenu(Scanner scanner) {
        System.out.println("""

                \033[1m[1]\033[0m Add advertisment agency
                \033[1m[2]\033[0m Add advertisment type
                \033[1m[3]\033[0m Add new advertisment
                \033[1m[4]\033[0m Display all advertisments
                \033[1m[5]\033[0m Display all advertisment of selected agency
                \033[1m[6]\033[0m Calculate agency's total cost
                \033[1m[7]\033[0m Display all advertisment counts per product
                \033[1m[8]\033[0m Calculate product's total cost
                \033[1m[9]\033[0m Display total cost for each product
                \033[1m[0]\033[0m Exit""");
        return read(scanner, "> ");
    }

    /**
     * Display the given message
     * 
     * @param message
     */
    static void displayError(String message) {
        System.out.println("Error: We are sorry but " + message + "!\n");
    }

    /**
     * Display a pre specified message
     * 
     * @param message
     */
    static void displayError() {
        displayError("the given selection is out of range");
    }

    /**
     * Make the given text bold
     * 
     * @param text
     * @return text with every character bold
     */
    public String bold(String text) {
        return String.format("\033[1m%s\033[0m", text);
    }

    public static void main(String[] args) {
        // Initialize used modules, variables and collections
        final Scanner in = new Scanner(System.in);
        String option;
        boolean done = false;
        int answer;

        AdCollection ads = new AdCollection();
        AdAgencyCollection adAgencies = new AdAgencyCollection();
        AdTypeCollection adTypes = new AdTypeCollection();
        ProductCollection products = new ProductCollection();

        String[][] agenciesData = new DataReader("company_list.txt", "COMPANY", new String[] { "AFM", "NAME" }, 2, true).read();
        String[][] productsData = new DataReader("item_list.txt", "ITEM", new String[] { "CODE", "DESCR", "SUPPLIER_AFM" }, 3, true).read();
        String[][] adTypesData = new DataReader("advtype_list.txt", "ADVTYPE", new String[] { "TYPE", "CODE", "DESCR", "AGENT_AFM" }, 4, true).read();
        String[][] printedAdsData = new DataReader("adv_list.txt", "ADV", new String[] { "TYPE", "DESCR", "AGENT_AFM", "ADVTYPE_CODE", "ITEM_CODE", "DURATION", "WORDS", "PAGE", "FIRST_PRICE", "MIDDLE_PRICE", "LAST_PRICE", "JUSTIFICATION"}, 11, false).read();
        String[][] radioTVAdsData = new DataReader("adv_list.txt", "ADV", new String[] { "TYPE", "DESCR", "AGENT_AFM", "ADVTYPE_CODE", "ITEM_CODE", "DURATION", "DUR_SEC", "TIME", "MORN_PRICE", "NOON_PRICE", "AFNOON_PRICE", "NIGHT_PRICE", "JUSTIFICATION"}, 12, false).read();
        String[][] internetAdsData = new DataReader("adv_list.txt", "ADV", new String[] { "TYPE", "DESCR", "AGENT_AFM", "ADVTYPE_CODE", "ITEM_CODE", "DURATION", "AUTO_ON", "EXTRA_PAGES", "PERDAY_PRICE", "AUTO_PRICE", "EXTRAPAGE_PRICE",  "JUSTIFICATION"}, 11, false).read();

        
        // Populate: Ad Agencies
        for (String[] entity : agenciesData) {
            adAgencies.add(new AdAgency(entity[0], entity[1]));
        }

        // Populate: Products
        for (String[] entity : productsData) {
            products.add(new Product(entity[1], entity[2]));
        }

        // Populate: Ad Types
        for (String[] entity : adTypesData) {
            adTypes.add(new AdType(entity[2], Integer.parseInt(entity[0]), entity[3]));
        }

        // Populate: Ads
        // Populate: Printed Ads
        for (String[] entity : printedAdsData) {
            if (Integer.parseInt(entity[0]) != 1) {
                continue;
            }
            int adTypeIndex = Integer.parseInt(entity[3]);
            int productCodeIndex = Integer.parseInt(entity[4]);
            if (adTypes.notInRange(adTypeIndex) || products.notInRange(productCodeIndex)) {
                continue;
            }
            ads.add(new PrintedAd(entity[1], entity[2], adTypes.get(adTypeIndex), products.get(productCodeIndex).code, Integer.parseInt(entity[5]), entity[11] != null && entity[11] != "" ? entity[11] : "Uknown" , Float.parseFloat(entity[8]), Float.parseFloat(entity[9]), Float.parseFloat(entity[10]), Integer.parseInt(entity[6]), Integer.parseInt(entity[7])));                
        }
        
        // Populate: Radio/Tv Ads
        for (String[] entity : radioTVAdsData) {
            if (Integer.parseInt(entity[0]) != 2) {
                continue;
            }
            int adTypeIndex = Integer.parseInt(entity[3]);
            int productCodeIndex = Integer.parseInt(entity[4]);
            if (adTypes.notInRange(adTypeIndex) || products.notInRange(productCodeIndex)) {
                continue;
            }
            ads.add(new RadTVAd(entity[1], entity[2], adTypes.get(adTypeIndex), products.get(productCodeIndex).code, Integer.parseInt(entity[5]), entity[12] != null && entity[12] != "" ? entity[12] : "Uknown" , Float.parseFloat(entity[8]), Float.parseFloat(entity[9]), Float.parseFloat(entity[10]), Float.parseFloat(entity[11]), Integer.parseInt(entity[6]), Integer.parseInt(entity[7])));                
        }
        
        // Populate: Internet Ads
        for (String[] entity : internetAdsData) {
            if (Integer.parseInt(entity[0]) != 3) {
                continue;
            }
            int adTypeIndex = Integer.parseInt(entity[3]);
            int productCodeIndex = Integer.parseInt(entity[4]);
            if (adTypes.notInRange(adTypeIndex) || products.notInRange(productCodeIndex)) {
                continue;
            }
            ads.add(new InternetAd(entity[1], entity[2], adTypes.get(adTypeIndex), products.get(productCodeIndex).code, Integer.parseInt(entity[5]), entity[11] != null && entity[11] != "" ? entity[11] : "Uknown" , Float.parseFloat(entity[8]), Float.parseFloat(entity[9]), Float.parseFloat(entity[10]), Integer.parseInt(entity[6]), Integer.parseInt(entity[7])));                
        }

        while (!done) {
            // Display app menu and read user's option
            option = displayMenu(in);

            // Save data and then exit
            if (option.equals("0")) {
                DataSaver.save("company", adAgencies.data());
                DataSaver.save("advtype", adTypes.data());
                DataSaver.save("item", products.data());
                DataSaver.save("adv", ads.data());
                
                System.out.println("We are sorry to see you go..");
                done = true;
            }

            // Create new ad agency
            else if (option.equals("1")) {

                System.out.println("Add new agency");

                // Read agency's data
                String name = read(in, "Enter Name: ");
                String AFM = read(in, "Enter AFM: ");

                // Greate object AdAgency and add to agencies collection
                adAgencies.add(new AdAgency(AFM, name));
                System.out.println("Agency added successfully!");

            }
            // Create new ad type
            else if (option.equals("2")) {

                System.out.println("Add new advertisment type");
                String name = read(in, "Enter Name: ");

                int type;
                do {
                    // Get user's selection for ad type
                    type = readInt(in, """
                            1. Printed Ad
                            2. Radio/Tv Ad
                            3. Internet Ad
                            >\s""");

                    if (!(1 <= type && type <= 3)) {
                        displayError();
                    }
                } while (!(1 <= type && type <= 3));

                do {
                    // List all ad agencies in the collection for the user to select one
                    System.out.println("Select ad agency:");
                    adAgencies.display();
                    answer = readInt(in, "> ");

                    // On user's selection error
                    if (adAgencies.notInRange(answer - 1)) {
                        displayError();
                    }
                } while (adAgencies.notInRange(answer - 1));

                // Retrieve the selected ad agency from the collection
                AdAgency adAgency = adAgencies.get(answer - 1);

                // Create new AdType and add to ad types collection
                adTypes.add(new AdType(name, type, adAgency.AFM));
                System.out.println("Type added successfully!");

            }
            // Create new ad
            else if (option.equals("3")) {
                System.out.println("Add new advertisment");

                do {
                    // List all ad types in the collection for the user to select one
                    System.out.println("Select ad type:");
                    adTypes.display();
                    answer = readInt(in, "> ");

                    // On user's selection error
                    if (adTypes.notInRange(answer - 1)) {
                        displayError();
                    }
                } while (adTypes.notInRange(answer - 1));

                // Retrieve the selected ad type from the collection
                AdType adType = adTypes.get(answer - 1);

                do {
                    // List all products in the collection for the user to select one
                    System.out.println("Select product:");
                    products.display();
                    answer = readInt(in, "> ");

                    // On user's selection error
                    if (products.notInRange(answer - 1)) {
                        displayError();
                    }
                } while (products.notInRange(answer - 1));

                // Retrieve the selected product from the collection
                Product product = products.get(answer - 1);

                String description = read(in, "Enter description: ");
                String reasoning = read(in, "Enter reasoning: ");

                do {
                    // List all ad agencies in the collection for the user to select one
                    System.out.println("Select ad agency:");
                    adAgencies.display();
                    answer = readInt(in, "> ");

                    // On user's selection error
                    if (adAgencies.notInRange(answer - 1)) {
                        displayError();
                    }
                } while (adAgencies.notInRange(answer - 1));

                // Retrieve the selected ad agency from the collection
                AdAgency adAgency = adAgencies.get(answer - 1);

                int daysDisplayed = readInt(in, "Enter number of displaying days: ");

                // Read ad data based on selected type
                // Read data for printed ad
                if (adType.isPrinted()) {
                    float firstPagePrice = readFloat(in, "Enter price for first page: ");
                    float middlePagePrice = readFloat(in, "Enter price for middle page: ");
                    float lastPagePrice = readFloat(in, "Enter price for last page: ");
                    int wordCount = readInt(in, "Enter word count: ");
                    int pageSelection = readInt(in, "Enter display page [1. First / 2. Middle / 3. Last]: ");
                    Ad ad = new PrintedAd(description, adAgency.AFM, adType, product.code, daysDisplayed, reasoning, firstPagePrice, middlePagePrice, lastPagePrice, wordCount, pageSelection);
                    ads.add(ad);
                }
                // Read data for radio/tv ad
                else if (adType.isRadTV()) {
                    float morningPrice = readFloat(in, "Enter morning price: ");
                    float noonPrice = readFloat(in, "Enter noon price: ");
                    float afternoonPrice = readFloat(in, "Enter afternoon price: ");
                    float nightPrice = readFloat(in, "Enter night price: ");
                    int durationInSec = readInt(in, "Enter duration (seconds): ");
                    int timeSelection = readInt(in,
                            "Select time of day [1. Morning / 2. Noon / 3. Afternoon / 4. Night]: ");
                    Ad ad = new RadTVAd(description, adAgency.AFM, adType, product.code, daysDisplayed, reasoning, morningPrice, noonPrice, afternoonPrice, nightPrice, durationInSec, timeSelection);
                    ads.add(ad);
                }
                // Read data for printed ad
                else if (adType.isInternetAd()) {
                    int autoDisplay = readInt(in, "Enable auto display [1. Yes / 2. No]: ");
                    float autoDisplayPrice = readFloat(in, "Enter auto display price: ");
                    int extraPages = readInt(in, "Enter number of extra pages: ");
                    float extraPagePrice = readFloat(in, "Enter extra pages price: ");
                    float pricePerDay = readFloat(in, "Enter price per day: ");
                    Ad ad = new InternetAd(description, adAgency.AFM, adType, product.code, daysDisplayed, reasoning, pricePerDay, autoDisplayPrice, extraPagePrice, autoDisplay, extraPages);
                    ads.add(ad);
                }

                System.out.println("Advertisment added successfully!");

            }
            // List all adds in the ad collection
            else if (option.equals("4")) {
                System.out.println("Display all advertisments");
                ads.display("\n");

            }
            // List all ads of given ad agency
            else if (option.equals("5")) {
                System.out.println("Display all advertisment of selected agency");

                do {
                    // List all ad agencies in the collection for the user to select one
                    System.out.println("Select ad agency:");
                    adAgencies.display();
                    answer = readInt(in, "> ");

                    // On user's selection error
                    if (adAgencies.notInRange(answer - 1)) {
                        displayError();
                    }
                } while (adAgencies.notInRange(answer - 1));

                // List all ads that use the agency's AFM located
                // at the index the user gave
                ads.displayAgencyAds(adAgencies.get(answer - 1).AFM);

            }
            // Display given agency's total cost
            else if (option.equals("6")) {
                System.out.println("Calculate agency's total cost");

                do {
                    // List all ad agencies in the collection for the user to select one
                    System.out.println("Select ad agency:");
                    adAgencies.display();
                    answer = readInt(in, "> ");

                    // On user's selection error
                    if (adAgencies.notInRange(answer - 1)) {
                        displayError();
                    }
                } while (adAgencies.notInRange(answer - 1));

                // Print the sum of costs of every ad that uses the given
                // agency's AFM
                ads.displayAgencyCost(adAgencies.get(answer - 1).AFM);

            }
            // Display all advertisment counts per product
            else if (option.equals("7")) {
                System.out.println("Display all advertisment counts per product");

                int[] codes = products.codes();
                ads.displayAllProductAdsCount(codes);

            }
            // Calculate product's total cost
            else if (option.equals("8")) {
                System.out.println("Calculate product's total cost");

                do {
                    // List all products in the collection for the user to select one
                    System.out.println("Select product:");
                    products.display();
                    answer = readInt(in, "> ");

                    // On user's selection error
                    if (products.notInRange(answer - 1)) {
                        displayError();
                    }
                } while (products.notInRange(answer - 1));

                Product product = (Product) products.get(answer - 1);
                ads.calculateProductCost(product.code, true);

            }
            // Display total cost for each product
            else if (option.equals("9")) {
                System.out.println("Display total cost for each product");

                int[] codes = products.codes();
                ads.displayAllProductCosts(codes);

            }
            // If option is not recognized
            else if (!option.equals("")) {
                displayError("the given option is not recognized");
            }
        }

        in.close();
    }
}
