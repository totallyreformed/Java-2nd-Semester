import java.util.ArrayList;

/**
 * A collection for containing Advertisment Types.
 */
public class AdTypeCollection extends Collection {
    AdTypeCollection() {
        super();
        list = new ArrayList<AdType>();
    }

    /**
     * Return the AdType located at [index]
     * in the collection.
     * 
     * @param index
     * @return
     */
    public AdType get(int index) {
        return (AdType) super.get(index);
    }

    /**
     * Get list data per line for saving purposes.
     * 
     * @return
     */
    public String[][] data() {
        String[][] data = new String[list.size()][4];

        AdType type;
        for (int i = 0; i < list.size(); i++) {
            type = get(i);

            data[i][0] = "TYPE " + type.type;
            data[i][1] = "CODE " + type.code;
            data[i][2] = "DESCR " + formatS(type.name);
            data[i][3] = "AGENT_AFM " + formatS(type.agency);
        }

        return data;
    }
}
