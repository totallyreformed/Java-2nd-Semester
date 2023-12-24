/**
 * A class to create product objects.
 */
public class Product {
    static private int globalCode = 0;

    protected int code;
    protected String description;
    protected String supplierAFM;

    public Product(String description, String supplierAFM) {
        globalCode++;
        this.code = globalCode;
        this.description = description;
        this.supplierAFM = supplierAFM;
    }

    /**
     * A toString method which provides a
     *custom implementation to generate a
     *string representation of a Product object
     *
     * @return string
     */
    public String toString() {
        return "Id Code: " + code + "\tDescription: " + description + "\tSupplier AFM: " + supplierAFM;
    }
}