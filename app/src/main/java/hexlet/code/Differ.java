package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static hexlet.code.Parser.parse;
import static hexlet.code.ChangesTree.buildChangesTree;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        String content1 = readFile(filepath1);
        String content2 = readFile(filepath2);
        String format1 = getFormatExtension(filepath1);
        String format2 = getFormatExtension(filepath2);

        if (content1.trim().isEmpty() && content2.trim().isEmpty()) {
            throw new IllegalArgumentException("Both input files are empty.");
        }
        if (content1.trim().isEmpty() || content2.trim().isEmpty()) {
            throw new IllegalArgumentException("One of input files is empty.");
        }

        Map<String, Object> firstFileToMap = getData(content1, format1);
        Map<String, Object> secondFileToMap = getData(content2, format2);

        List<ChangeBranch> differMap = buildChangesTree(firstFileToMap, secondFileToMap);
        return Formatter.format(differMap, format);
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

    public static String readFile(String filepath) throws Exception {
        Path path = Paths.get(filepath);
        return new String(Files.readAllBytes(path));
    }

    public static Map<String, Object> getData(String content, String format) throws Exception {
        if (content == null || content.trim().isEmpty()) {
            throw new IllegalArgumentException("Input content is empty or contains only whitespace");
        }
        return parse(content, format);
    }

    public static String getFormatExtension(String file) {
        return file.substring(file.lastIndexOf(".") + 1);
    }
}
