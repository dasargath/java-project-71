package hexlet.code.formatters;

import hexlet.code.ChangeBranch;
import java.util.List;

public class Plain {
    public static String format(List<ChangeBranch> differTree) {
        StringBuilder result = new StringBuilder();
        for (ChangeBranch element : differTree) {
            String key = element.getKey();
            String changeType = element.getChangeType();
            Object oldValue = element.getOldValue();
            Object newValue = element.getNewValue();

            String message = switch (changeType) {
                case "added" -> "Property '" + key + "' was added with value: " + getValue(newValue) + "\n";
                case "deleted" -> "Property '" + key + "' was removed\n";
                case "changed" -> "Property '" + key + "' was updated. From " + getValue(oldValue)
                        + " to " + getValue(newValue) + "\n";
                default -> "";
            };
            result.append(message);
        }
        return result.toString().trim();
    }

    private static String getValue(Object value) {
        if (value instanceof String) {
            return "'" + value + "'";
        } else if (value == null) {
            return "null";
        } else if (!(value instanceof Integer) && !(value instanceof Boolean)) {
            return "[complex value]";
        }
        return value.toString();
    }
}
