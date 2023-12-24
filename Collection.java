import java.util.ArrayList;

/**
 * A collection for containing objects.
 */
public class Collection {
    /**
     * An array list containing all the objects of the collection.
     */
    protected ArrayList list;

    /**
     * Add given object to the collection.
     * 
     * @param object
     */
    public void add(Object object) {
        list.add(object);
    }

    /**
     * Check if given index is in range of the collection size.
     * 
     * @param index
     * @return boolean
     */
    public boolean inRange(int index) {
        return 0 <= index && index < list.size();
    }

    /**
     * inRange() reversed.
     * 
     * @param index
     * @return boolean
     */
    public boolean notInRange(int index) {
        return !inRange(index);
    }

    /**
     * Return the object located at [index]
     * in the collection.
     * 
     * @param index
     * @return
     */
    public Object get(int index) {
        return list.get(index);
    }

    /**
     * Returns {@code true} if this list contains no elements.
     * 
     * @return {@code true} if this list contains no elements
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Return the last object in the collection.
     * 
     * @param index
     * @return
     */
    public Object getLast() {
        return list.get(list.size() - 1);
    }

    /**
     * Print to screen all the objects of the collection.
     * Set the way the index is shown by setting the promt parameter.
     * Use "%i" for describing the index.
     * Example:
     * "%i. " -> "0. [Object]" .. "N. [Object]"
     * "" -> "[Object]" .. "[Object]"
     * "--- %i ---\n" -> "--- 0 ---\n[Object]" .. "--- N ---\n[Object]".
     * 
     * @param promt
     */
    public void display(String promt) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(promt.replace("%i", String.valueOf(i + 1)) + list.get(i));
        }
    }

    /**
     * Call the display(String promt) method with
     * default promt: "%i. ".
     */
    public void display() {
        display("\033[1m%i. \033[0m");
    }

    /**
     * Format given String value to "value"
     * 
     * @param value
     * @return
     */
    public String formatS(String value) {
        return String.format("\"%s\"", value);
    }

    /**
     * Sort the two given tables from bigger to smaller
     * based on the table values. (float)
     * 
     * @param keys
     * @param values
     */
    static void sort(int[] keys, float[] values) {
        final int n = keys.length;
        for (int i = 0; i < values.length; i++) {
            for (int j = n - 1; j > i; j--) {
                if (values[i] < values[j]) {
                    float tmpInt = values[i];
                    values[i] = values[j];
                    values[j] = tmpInt;

                    int tmpString = keys[i];
                    keys[i] = keys[j];
                    keys[j] = tmpString;
                }
            }
        }
    }

    /**
     * Sort the two given tables from bigger to smaller
     * based on the table values. (int)
     * 
     * @param keys
     * @param values
     */
    static void sort(int[] keys, int[] values) {
        final int n = keys.length;
        for (int i = 0; i < values.length; i++) {
            for (int j = n - 1; j > i; j--) {
                if (values[i] < values[j]) {
                    int tmpInt = values[i];
                    values[i] = values[j];
                    values[j] = tmpInt;

                    int tmpString = keys[i];
                    keys[i] = keys[j];
                    keys[j] = tmpString;
                }
            }
        }
    }
}