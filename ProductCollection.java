import java.util.ArrayList;

/**
 * A collection for containing Products.
 */
public class ProductCollection extends Collection {
    ProductCollection() {
        super();
        list = new ArrayList<Product>();
    }

    /**
     * Return the Product located at [index]
     * in the collection.
     * 
     * @param index
     * @return
     */
    public Product get(int index) {
        return (Product) super.get(index);
    }

    /**
     * Get every product's code.
     * 
     * @return String[] codes
     */
    public int[] codes() {
        final int[] codes = new int[list.size()];

        // Iterate for every product in the list
        // and add it's code to the codes array.
        for (int i = 0; i < list.size(); i++) {
            Product product = (Product) list.get(i);
            codes[i] = product.code;
        }

        return codes;
    }

    /**
     * Get list data per line for saving purposes.
     * 
     * @return
     */
    public String[][] data() {
        String[][] data = new String[list.size()][3];

        Product product;
        for (int i = 0; i < list.size(); i++) {
            product = get(i);

            data[i][0] = "CODE " + product.code;
            data[i][1] = "DESCR " + formatS(product.description);
            data[i][2] = "SUPPLIER_AFM " + formatS(product.supplierAFM);
        }

        return data;
    }
}
