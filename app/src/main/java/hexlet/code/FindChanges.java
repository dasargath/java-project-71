package hexlet.code;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class FindChanges {
    public static String compareJSONS(Map<String, Object> jsonData1, Map<String, Object> jsonData2) {
        TreeMap<String, Object> sortedDataMap = new TreeMap<>(jsonData1);
        sortedDataMap.putAll(jsonData2);

        StringBuilder compareOutput = new StringBuilder();
        compareOutput.append("{");

        Set<String> keys = sortedDataMap.keySet();
        int count = 0;
        for (String key : keys) {
            if (count > 0) {
                compareOutput.append(",");
            }
            compareOutput.append("\n");

            Object value1 = jsonData1.get(key);
            Object value2 = jsonData2.get(key);
            compareOutput.append(generateComparisonLine(key, value1, value2));
            count++;
        }

        if (!keys.isEmpty()) {
            compareOutput.append("\n");
        }
        compareOutput.append("}");
        return compareOutput.toString().trim();
    }

    private static String generateComparisonLine(String key, Object value1, Object value2) {
        if (value1 == null) {
            return String.format("+ %s: %s", quoteIfNeeded(key), quoteIfNeeded(value2));
        } else if (value2 == null) {
            return String.format("- %s: %s", quoteIfNeeded(key), quoteIfNeeded(value1));
        } else if (!value1.equals(value2)) {
            return String.format("- %s: %s,\n+ %s: %s", quoteIfNeeded(key), quoteIfNeeded(value1), quoteIfNeeded(key),
                    quoteIfNeeded(value2));
        } else {
            return String.format("  %s: %s", quoteIfNeeded(key), quoteIfNeeded(value1));
        }
    }

    private static String quoteIfNeeded(Object value) {
        if (value instanceof Integer || value instanceof Boolean) {
            return value.toString();
        }
        return "\"" + value + "\"";
    }
}
