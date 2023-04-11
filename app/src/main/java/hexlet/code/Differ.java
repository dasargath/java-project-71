package hexlet.code;

public class Differ {
    public static String generate(String filePath1, String filePath2, String format) {
        var diff = Differ.generate(filePath1, filePath2, format);
        System.out.println(diff);
        return diff;
    }
}
