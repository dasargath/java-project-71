package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;

public class TestDiffer {

    private final String jsonFilePath2 = "src/test/resources/file2.json";
    private final String jsonFilePath1 = "src/test/resources/file1.json";
    private final String formatName = "stylish";

    @Test
    public void testDifferGenerateIdenticalFiles() throws IOException {
        String expectedResult = "{\n"
                + "  \"follow\": false,\n"
                + "  \"host\": \"hexlet.io\",\n"
                + "  \"proxy\": \"123.234.53.22\",\n"
                + "  \"timeout\": 50\n"
                + "}";

        String actualResult = Differ.generate(jsonFilePath1, jsonFilePath1, formatName);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testDifferGenerateDiffFiles() throws IOException {
        String expectedResult = "{\n"
                + "- \"follow\": false,\n"
                + "  \"host\": \"hexlet.io\",\n"
                + "- \"proxy\": \"123.234.53.22\",\n"
                + "- \"timeout\": 50,\n"
                + "+ \"timeout\": 20,\n"
                + "+ \"verbose\": true\n"
                + "}";

        String actualResult = Differ.generate(jsonFilePath1, jsonFilePath2, formatName);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testGenerateWithValidFiles() throws IOException {
        String expectedResult = "{\n"
                + "- \"follow\": false,\n"
                + "  \"host\": \"hexlet.io\",\n"
                + "- \"proxy\": \"123.234.53.22\",\n"
                + "- \"timeout\": 50,\n"
                + "+ \"timeout\": 20,\n"
                + "+ \"verbose\": true\n"
                + "}";
        String actualResult = Differ.generate(jsonFilePath1, jsonFilePath2, formatName);
        assertEquals(expectedResult, actualResult);
    }

}
