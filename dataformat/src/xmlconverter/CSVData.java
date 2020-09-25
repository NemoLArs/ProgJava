package xmlconverter;

import java.io.BufferedReader;
import java.util.List;
import java.util.stream.Collectors;

public class CSVData {
    private final List<String> lines;
    private final String[] categories;

    public CSVData(BufferedReader reader) {
        this.lines = reader.lines().collect(Collectors.toList());
        this.categories = formatCategories(lines.get(0));
    }

    public List<String> getLines() {
        return lines;
    }

    public String[] getCategories() {
        return categories;
    }

    /**
     * Assumes that the first line is the columns. Filter out illegal characters for xml files
     * Hopefully that's all illegal characters - If not, there will be errors
     * @param line The initial line to get categories from
     * @return with illegal characters escaped
     */
    static String[] formatCategories(String line) {
        return line
                .replace("\"", "")
                .replace(" ", "")
                .replace("#", "")
                .replace("(", "_x0028_")
                .replace(")", "_x0029_")
                .replace("?", "")
                .split(",");
    }
}
