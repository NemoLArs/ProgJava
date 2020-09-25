package xmlconverter;

public class Utils {

    private Utils() {
        throw new IllegalStateException("Util class, not to be initialized");
    }

    /**
     * @param values list of values
     * @param index index to get value from
     * @return correct value based on index, empty string if index is out of range
     * If the row is empty - Skip it
     */
    static String getValue(String[] values, int index) {
        String value = "";
        if (values.length > index && index >= 0) {
            value = values[index];
        }
        return value;
    }
}
