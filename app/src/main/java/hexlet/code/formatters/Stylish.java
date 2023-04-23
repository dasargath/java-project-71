package hexlet.code.formatters;

import hexlet.code.ChangeBranch;

import java.util.List;

public class Stylish {
    public static String format(List<ChangeBranch> differTree) {
        StringBuilder result = new StringBuilder("{\n");

        for (ChangeBranch element : differTree) {
            String key = element.getKey();
            String typeOfChange = element.getChangeType();
            Object oldValue = element.getOldValue();
            Object newValue = element.getNewValue();

            String message = switch (typeOfChange) {
                case "added" -> "  + " + key + ": " + newValue + "\n";
                case "deleted" -> "  - " + key + ": " + oldValue + "\n";
                case "changed" -> "  - " + key + ": " + oldValue + "\n" + "  + " + key + ": " + newValue + "\n";
                case "unchanged" -> "    " + key + ": " + newValue + "\n";
                default -> "";
            };
            result.append(message);
        }
        result.append("}");
        return result.toString();
    }
}
