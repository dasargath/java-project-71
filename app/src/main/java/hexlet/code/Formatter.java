package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Stylish;
import hexlet.code.formatters.Plain;
import java.util.List;

public class Formatter {
    public static String format(List<ChangeBranch> differTree, String formatName) throws Exception {
        return switch (formatName) {
            case "stylish" -> Stylish.format(differTree);
            case "json" -> Json.format(differTree);
            case "plain" -> Plain.format(differTree);
            default -> {
                String message = String.format("Unknown format name: %s. Choose 'stylish', 'json' or 'plain'",
                        formatName);
                throw new Exception(message);
            }
        };
    }
}
