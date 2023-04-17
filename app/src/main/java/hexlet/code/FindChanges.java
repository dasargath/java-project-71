package hexlet.code;

import java.util.Map;
import java.util.TreeMap;
import java.util.Set;

public class FindChanges {
    public static String compareJSONS(Map<String, Object> jsonData1, Map<String, Object> jsonData2) {
        TreeMap<String, Object> sortedDataMap = new TreeMap<>(jsonData1);
        sortedDataMap.putAll(jsonData2);

        StringBuilder compareOutput = new StringBuilder();
        compareOutput.append("{");

        Set<String> keys = sortedDataMap.keySet();
        int count = 0;
        for (String key : keys) {
            if (count == 0) {
                compareOutput.append("\n");
            }
            count++;
            String sign = "  ";
            Object value1 = jsonData1.get(key);
            Object value2 = jsonData2.get(key);

            if (value1 == null) {
                sign = "+ ";
            } else if (value2 == null) {
                sign = "- ";
            } else if (!value1.equals(value2)) {
                compareOutput.append("- ").append(quoteIfNeeded(key)).append(": ").append(quoteIfNeeded(value1))
                        .append(",\n");
                sign = "+ ";
            }
            compareOutput.append(sign).append(quoteIfNeeded(key)).append(": ")
                    .append(quoteIfNeeded(value2 != null ? value2 : value1));

            if (count < keys.size()) {
                compareOutput.append(",\n");
            }
        }
        if (!keys.isEmpty()) {
            compareOutput.append("\n");
        }
        compareOutput.append("}");
        return compareOutput.toString().trim();
    }

    private static String quoteIfNeeded(Object value) {
        if (value instanceof Integer || value instanceof Boolean) {
            return value.toString();
        }
        return "\"" + value + "\"";
    }
}
