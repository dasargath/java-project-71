package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class Differ {
    public static String generate(String filepath1, String filepath2, String formatName) throws IOException {
        String fileContent1 = Files.readString(Path.of(filepath1));
        String fileContent2 = Files.readString(Path.of(filepath2));

        ObjectMapper objectMapper = new ObjectMapper();

        TypeReference<Map<String, Object>> typeRef1 = new TypeReference<Map<String, Object>>() { };
        TypeReference<Map<String, Object>> typeRef2 = new TypeReference<Map<String, Object>>() { };
        Map<String, Object> jsonData1 = objectMapper.readValue(fileContent1, typeRef1);
        Map<String, Object> jsonData2 = objectMapper.readValue(fileContent2, typeRef2);

        return FindChanges.compareJSONS(jsonData1, jsonData2);
    }
}
