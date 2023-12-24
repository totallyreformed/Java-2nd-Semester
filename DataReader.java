import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataReader {
    private String path;
    private String entityName;
    private int entityCount = 0;
    private String[] tags;
    private int mandatory;
    private boolean displayWarnings;

    /**
     * 
     * @param path       Name of file
     * @param entityName Name of entity save in file
     * @param tags       Tags that an entity may have
     * @param mandatory  Count of mandatory tags, (mandatory tags should be placed
     *                   at the start of the tags array)
     */
    public DataReader(String path, String entityName, String[] tags, int mandatory, boolean displayWarnings) {
        this.path = path;
        this.entityName = entityName;
        this.tags = tags;
        this.mandatory = mandatory;
        this.displayWarnings = displayWarnings;
    }

    /**
     * Check if given line has the same value as the given value.
     * Letter casing does not matter.
     * 
     * @param line
     * @param value
     * @return {@code true} if line equals or {@code false} if not
     */
    public boolean lineEquals(String line, String value) {
        return line.toUpperCase().equals(value.toUpperCase());
    }

    /**
     * Check if given line starts with the given value.
     * Letter casing does not matter.
     * 
     * @param line
     * @param value
     * @return {@code true} if line equals or {@code false} if not
     */
    public boolean lineStarts(String line, String value) {
        return line.toUpperCase().startsWith(value.toUpperCase() + " ");
    }

    /**
     * Using the {@code readFile()} function, get the file lines
     * from the bufferedReader. Return a String[] array containing
     * all the sanitized (pre-trimed and non-empty) lines.
     * 
     * @return {@code String[]} or {@code null} if exception is thrown.
     */
    public String[] getLines() {
        ArrayList<String> linesList = new ArrayList<>();
        String line;

        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(new File(path)));

            while ((line = reader.readLine()) != null) {

                // Add line to list
                linesList.add(line.trim());
            }
        } catch (IOException e) {
            System.out.println("Error: File \"" + path + "\" does not exist.");
            // e.printStackTrace();
        }

        // Create an array from the arrayList
        return linesList.toArray(new String[linesList.size()]);
    }

    /**
     * Check if the given lines are compling whith the rules.
     * 
     * @param linesArray
     * @return {@code true} or {@code false}
     */
    public boolean checkCompliance(String[] linesArray) {

        // Check that the first non empty line is the *entityName*_LIST
        for (int i = 0; i < linesArray.length; i++) {
            if (!linesArray[i].isEmpty()) {
                if (!lineEquals(linesArray[i], String.format("%s_LIST", entityName))) {
                    System.out.println("Error: (\"" + path + "\") List name is wrong. The file could not be read.");
                    return false;
                }
                break;
            }
        }

        // Count how many brackets "{}" are openned ("{")
        // and how many are closed ("}")
        int countOpened = 0;
        int countClosed = 0;

        for (String line : linesArray) {
            if (lineEquals(line, "{")) {
                countOpened++;
            }
            if (lineEquals(line, "}")) {
                countClosed++;
            }
        }

        // Check that all brackets that were opened got closed
        if (countOpened != countClosed) {
            System.out.println(
                    "Error: (\"" + path + "\") Some brackets never closed or opened. The file could not be read.");
            return false;
        }

        entityCount = countOpened;

        return true;
    }

    /**
     * Get data from an entity.
     * 
     * @param lines
     * @param start
     * @param end
     * @return {@code String[]} entity data or {@null} if a mandatory tag is
     *         missing.
     */
    public String[] getEntityData(String[] lines, int start, int end) {
        String[] data = new String[tags.length];
        String tag, rawLine, value;
        boolean found;

        // Iterate through every tag in the given tags array.
        // This way, the found values will be in the same order as
        // the given tags
        for (int tagIndex = 0; tagIndex < tags.length; tagIndex++) {
            tag = tags[tagIndex];
            found = false;
            for (int index = start; index <= end; index++) {
                rawLine = lines[index];

                // If line starts with the tag name
                if (lineStarts(rawLine, tag)) {
                    // Remove the quoatation marks from the line "
                    value = rawLine.substring(tag.length()).trim().replace("\"", "");

                    // Add value to the specified index at the data array
                    data[tagIndex] = value;

                    // Mark tag as found
                    found = true;
                }
            }
            // If a mandatory tag was not found the this entry
            // can not be read
            if (!found && (tagIndex < mandatory)) {
                // Print error message
                if (displayWarnings) {
                    System.out.printf("""
                            Warning: Entry at file \"%s\",  lines %s..%s could not be read.
                            Cause: Missing mandatory tag \"%s\".
                                """, path, (start + 1), (end + 1), tag);
                }
                return null;
            }
        }

        return data;
    }

    /**
     * Get all entitie's data from file.
     * 
     */
    public String[][] read() {
        // Get the lines
        String[] linesArray = getLines();

        if (linesArray.length == 0) {
            return new String[][] {};
        }

        // Check lines compliance
        boolean complies = checkCompliance(linesArray);

        // If lines are not complying, terminate data retrieval
        if (!complies) {
            return new String[][] {};
        }

        ArrayList<String[]> entitiesData = new ArrayList<>();
        String line;
        int index, start, end;
        boolean readingEntity = false;
        start = end = -1;
        index = 0;
        String[] entityData = null;
        while (index < linesArray.length) {
            line = linesArray[index];
            // Mark start for reading entity data
            if (lineEquals(line, entityName)) {
                start = index;
                readingEntity = true;
            }
            // Mark end
            if (readingEntity && lineEquals(line, "}")) {
                end = index;

                if (end > start) {
                    // Read data
                    entityData = getEntityData(linesArray, start, end);
                    if (entityData != null) {
                        // Add data to list
                        entitiesData.add(entityData);
                    }
                }

                readingEntity = false;
            }

            index++;
        }

        if (displayWarnings && (entitiesData.size() - 1 != entityCount - 2)) {
            System.out.println("Warning: (\"" + path + "\") One or more entities might not have been read.");
        }

        return entitiesData.toArray(new String[entitiesData.size()][tags.length]);
    }

}
