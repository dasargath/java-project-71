package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class Differ {
    public static String generate(String filepath1, String filepath2, String formatName) throws IOException {
        String fileContent1 = Files.readString(Path.of(filepath1));
        String fileContent2 = Files.readString(Path.of(filepath2));

        Map<String, Object> jsonData1 = new ObjectMapper().readValue(fileContent1, Map.class);
        Map<String, Object> jsonData2 = new ObjectMapper().readValue(fileContent2, Map.class);

        return FindChanges.compareJSONS(jsonData1, jsonData2);
    }
}
