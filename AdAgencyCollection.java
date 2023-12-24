import java.util.ArrayList;

/**
 * A collection for containing Advertisment Agencies.
 */
public class AdAgencyCollection extends Collection {
    AdAgencyCollection() {
        super();
        list = new ArrayList<AdAgency>();
    }

    /**
     * Return the AdAgency located at [index]
     * in the collection.
     * 
     * @param index
     * @return
     */
    public AdAgency get(int index) {
        return (AdAgency) super.get(index);
    }

    /**
     * Get list data per line for saving purposes.
     * 
     * @return
     */
    public String[][] data() {
        String[][] data = new String[list.size()][2];

        AdAgency agency;
        for (int i = 0; i < list.size(); i++) {
            agency = get(i);

            data[i][0] = "AFM " + formatS(agency.AFM);
            data[i][1] = "NAME " + formatS(agency.name);
        }

        return data;
    }
}