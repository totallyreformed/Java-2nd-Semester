import java.io.FileWriter;
import java.io.IOException;

public class DataSaver {

    /**
     * Using the {@code entityName} create a file with the
     * name of {@code entityname_list.txt}. Write to the file
     * each line contained in the {@code String[][] data} array.
     * 
     * The String[][] data array must contain for every item that will
     * be written a String[] array containing it's tags (lines).
     * 
     * Example:
     * {
     * {"CODE 1001", "ITEM_COUNT 2", ...},
     * {tag1, tag2, ...},
     * ...
     * }
     * 
     * @param entityName
     * @param data
     */
    static public void save(String entityName, String[][] data) {
        // Create the @path variable using the @entityName given
        // @path will be lower case.
        String path = String.format("%s_list.txt", entityName.toLowerCase());

        // Set @entityName to upper case
        entityName = entityName.toUpperCase();

        // Instantiate the FileWriter writer
        FileWriter writer = null;

        try {
            // Create new FileWriter
            writer = new FileWriter(path);

            // Write the name of the list at the start of the file
            writer.write(String.format("%s_LIST\n{\n", entityName));

            // Iterate for every String[] entityData
            // contained in the String[][] @data array
            for (String[] entityData : data) {

                // Write the entity's name and a openning bracket ("{") in a new line
                writer.write(String.format("\t%s\n\t{\n", entityName));

                // For every String tagData in the String[] entityData
                // check if its null or "" and if not write to file.
                for (String tagData : entityData) {
                    if (tagData != null && tagData != "") {

                        // Write tag
                        writer.write(String.format("\t\t%s\n", tagData));
                    }
                }

                // Write the closing bracket ("}") for the entity
                writer.write("\t}\n");
            }

            // Write the closing bracket ("}") for the list
            writer.write("}");

            // Close the writer
            writer.close();

            System.err.println("Success: Wrote data to file \"" + path + "\".");
        } catch (

        IOException e) {
            System.err.println("Error: Could not write to file \"" + path + "\".");
        }
    }
}
