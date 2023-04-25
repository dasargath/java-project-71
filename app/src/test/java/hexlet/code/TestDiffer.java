package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestDiffer {

    private static String expectedJson;
    private static String expectedYml;
    private static String expectedLongJson;
    private static String expectedLongYml;
    private static String expectedOutputJson;
    private static String expectedPlain;

    @BeforeAll
    public static void beforeAll() throws Exception {
        expectedJson = Files.readString(Paths.get("src/test/resources/expectedJson.json"));
        expectedYml = Files.readString(Paths.get("src/test/resources/expectedYml"));
        expectedLongJson = Files.readString(Paths.get("src/test/resources/expectedLongStylishJson"));
        expectedLongYml = Files.readString(Paths.get("src/test/resources/expectedLongStylishYml"));
        expectedOutputJson = Files.readString(Paths.get("src/test/resources/outputJson.json"));
        expectedPlain = Files.readString(Paths.get("src/test/resources/plain"));
    }

    @ParameterizedTest
    @CsvSource({"json, file1.json, file2.json, expectedJson", "yml, file1.yml, file2.yml, expectedYml"})
    public void testDefault(String format, String file1, String file2, String expectedResult) throws Exception {
        String actual = Differ.generate(getResourcePath(file1), getResourcePath(file2));
        assertEquals(actual, format.equals("json") ? expectedJson : expectedYml);
    }

    @ParameterizedTest
    @CsvSource({"json, fileLong1.json, fileLong2.json, plain", "yml, fileLong1.yml, fileLong2.yml, plain"})
    public void testFormatToPlain(String format, String file1, String file2, String expectedResult) throws Exception {
        String actual = Differ.generate(getResourcePath(file1), getResourcePath(file2), "plain");
        assertEquals(actual, expectedPlain);
    }

    @ParameterizedTest
    @CsvSource({"json, file1.json, file2.json, outputJson.json", "yml, file1.yml, file2.yml, outputJson.json"})
    public void testFormatToJson(String format, String file1, String file2, String expectedResult) throws Exception {
        String actual = Differ.generate(getResourcePath(file1), getResourcePath(file2), "json");
        assertEquals(actual, expectedOutputJson);
    }

    @ParameterizedTest
    @CsvSource({"json, fileLong1.json, fileLong2.json, expectedLongStylishJson",
        "yml, fileLong1.yml, fileLong2.yml, expectedLongStylishYml"})
    public void testLongFormats(String format, String fileLong1, String fileLong2, String expectedResult)
            throws Exception {
        String actual = Differ.generate(getResourcePath(fileLong1), getResourcePath(fileLong2));
        assertEquals(actual, format.equals("json") ? expectedLongJson : expectedLongYml);
    }

    @ParameterizedTest
    @CsvSource({"json, empty1.json, empty2.json", "yml, empty1.yml, empty2.yml"})
    public void testBothEmpty(String format, String file1, String file2) {
        assertThrows(IllegalArgumentException.class, () -> Differ.generate(getResourcePath(file1),
                getResourcePath(file2)));
    }

    @ParameterizedTest
    @CsvSource({"json, empty1.json, file2.json, file1.json, empty1.json",
        "yml, empty1.yml, file2.yml, file1.yml, empty1.yml"})
    public void testOneEmpty(String format, String emptyFile, String file1, String file2, String file3) {
        assertThrows(IllegalArgumentException.class, () -> Differ.generate(getResourcePath(emptyFile),
                getResourcePath(file1)));
        assertThrows(IllegalArgumentException.class, () -> Differ.generate(getResourcePath(file2),
                getResourcePath(file3)));
    }

    private String getResourcePath(String fileName) {
        return Paths.get("src/test/resources", fileName).toString();
    }
}
