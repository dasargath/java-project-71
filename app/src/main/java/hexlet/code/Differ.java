package hexlet.code;

import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.Map;
import java.util.TreeMap;

public class Differ {
    public static String generate(String filePath1, String filePath2, String format) throws Exception{
        Path filepath1 = Paths.get(filePath1);
        Path filepath2 = Paths.get(filePath2);
        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> file1Content = new TreeMap<>();
        Map<String, Object> file2Content = new TreeMap<>();

        try {
            file1Content = objectMapper.readValue(filepath1.toFile(), Map.class);
            file2Content = objectMapper.readValue(filepath2.toFile(), Map.class);
        } catch (IOException e) {
            System.out.println("File read error: " + e.getMessage());
        }

        return FindChanges.compareJSONS(file1Content, file2Content);
    }
}
