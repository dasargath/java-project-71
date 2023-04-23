package hexlet.code;

import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

public class Parser {
    private static final ObjectMapper OBJECT_MAPPER_JSON = new ObjectMapper();
    private static final ObjectMapper OBJECT_MAPPER_YAML = new ObjectMapper(new YAMLFactory());

    public static Map<String, Object> parse(String content, String formatFile) throws Exception {
        String format = formatFile.toLowerCase();
        switch (format) {
            case "json" -> {
                return parseJson(content);
            }
            case "yml", "yaml" -> {
                return parseYml(content);
            }
            default -> throw new Exception("Unknown format: '" + formatFile + "'");
        }
    }

    public static Map<String, Object> parseJson(String content) throws JsonProcessingException {
        TypeReference<Map<String, Object>> typeRefJson = new TypeReference<Map<String, Object>>() { };
        return OBJECT_MAPPER_JSON.readValue(content, typeRefJson);
    }

    public static Map<String, Object> parseYml(String content) throws JsonProcessingException {
        TypeReference<Map<String, Object>> typeRefYml = new TypeReference<Map<String, Object>>() { };
        return OBJECT_MAPPER_YAML.readValue(content, typeRefYml);
    }
}
