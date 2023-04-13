package hexlet.code;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.Set;

public class FindChanges {
    public static String compareJSONS (Map<String, Object> jsonData1, Map<String, Object> jsonData2) {
        TreeMap<String, Object> sortedDataMap = new TreeMap<>(jsonData1);
        sortedDataMap.putAll(jsonData2);

        StringBuilder compareOutput = new StringBuilder();
        compareOutput.append("{\n");


        Set<String> keys = sortedDataMap.keySet();
        for (String key : keys) {
            String sign = "  ";
            Object value1 = jsonData1.get(key);
            Object value2 = jsonData2.get(key);

            if (value1 == null) {
                sign = "+ ";
            } else if (value2 == null) {
                sign = "- ";
            } else if (!value1.equals(value2)) {
                compareOutput.append("- ").append(key).append(": ").append(value1).append("\n");
                sign = "+ ";
            }
            compareOutput.append(sign).append(key).append(": ").append(Objects.requireNonNullElse(value2, value1))
                    .append("\n");
        }
        compareOutput.append("}");
        return compareOutput.toString();
    }

}
