package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;


public class TestFindChanges {

    private final String expectJson = "{\n"
            + "- \"follow\": false,\n"
            + "  \"host\": \"hexlet.io\",\n"
            + "- \"proxy\": \"123.234.53.22\",\n"
            + "- \"timeout\": 50,\n"
            + "+ \"timeout\": 20,\n"
            + "+ \"verbose\": true\n"
            + "}";


    private final String jsonFilePath2 = ("src/test/resources/file2.json");
    private final String jsonFilePath1 = ("src/test/resources/file1.json");


    @Test
    void testCompareJSONS() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> json1 = objectMapper.readValue(Paths.get(jsonFilePath1).toFile(), Map.class);
        Map<String, Object> json2 = objectMapper.readValue(Paths.get(jsonFilePath2).toFile(), Map.class);
        String actualResult = FindChanges.compareJSONS(json1, json2);
        assertEquals(expectJson, actualResult, "Expected and actual results do not match");
    }

    @Test
    void testCompareJSONSEmptyObject() throws Exception {
        Map<String, Object> json1 = new HashMap<>();
        Map<String, Object> json2 = new HashMap<>();

        String expectedJson = "{}";
        String actualResult = FindChanges.compareJSONS(json1, json2);
        assertEquals(expectedJson, actualResult, "Expected an actual result");
    }
    @Test
    void testCompareJSONSIdenticalObjects() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> jsonData1 = objectMapper.readValue(Paths.get(jsonFilePath1).toFile(), Map.class);
        String expectedJson = "{\n"
                + "  \"follow\": false,\n"
                + "  \"host\": \"hexlet.io\",\n"
                + "  \"proxy\": \"123.234.53.22\",\n"
                + "  \"timeout\": 50\n"
                + "}";
        String actualResult = FindChanges.compareJSONS(jsonData1, jsonData1);
        assertEquals(expectedJson, actualResult);
    }
    @Test
    public void testCompareJSONSAllDifferentObjects() {
        Map<String, Object> jsonData1 = new HashMap<>();
        jsonData1.put("key1", "value1");

        Map<String, Object> jsonData2 = new HashMap<>();
        jsonData2.put("key2", "value2");

        String expectedResult = "{\n"
                + "- \"key1\": \"value1\",\n"
                + "+ \"key2\": \"value2\"\n"
                + "}";
        assertEquals(expectedResult, FindChanges.compareJSONS(jsonData1, jsonData2));
    }
}
