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

    /**
     * Checks if the output for the default input files matches the JSON/YML format.
     *
     * @param format         The file format ("json" or "yml").
     * @param file1          The first input file.
     * @param file2          The second input file.
     * @param expectedResult The expected result variable name.
     */
    @ParameterizedTest
    @CsvSource({"json, file1.json, file2.json, expectedJson", "yml, file1.yml, file2.yml, expectedYml"})
    public void testDefault(String format, String file1, String file2, String expectedResult) throws Exception {
        String actual = Differ.generate(getResourcePath(file1), getResourcePath(file2));
        assertEquals(actual, format.equals("json") ? expectedJson : expectedYml);
    }

    /**
     * Checks if the output for the default input files matches the plain format.
     *
     * @param format         The file format ("json" or "yml").
     * @param file1          The first input file.
     * @param file2          The second input file.
     * @param expectedResult The expected result variable name.
     */
    @ParameterizedTest
    @CsvSource({"json, fileLong1.json, fileLong2.json, plain", "yml, fileLong1.yml, fileLong2.yml, plain"})
    public void testFormatToPlain(String format, String file1, String file2, String expectedResult) throws Exception {
        String actual = Differ.generate(getResourcePath(file1), getResourcePath(file2), "plain");
        assertEquals(actual, expectedPlain);
    }

    /**
     * Checks if the output for the default input files matches the JSON/YML format using the "json" flag.
     *
     * @param format         The file format ("json" or "yml").
     * @param file1          The first input file.
     * @param file2          The second input file.
     * @param expectedResult The expected result variable name.
     */
    @ParameterizedTest
    @CsvSource({"json, file1.json, file2.json, outputJson.json", "yml, file1.yml, file2.yml, outputJson.json"})
    public void testFormatToJson(String format, String file1, String file2, String expectedResult) throws Exception {
        String actual = Differ.generate(getResourcePath(file1), getResourcePath(file2), "json");
        assertEquals(actual, expectedOutputJson);
    }

    /**
     * Checks if the output for the long input files matches the JSON/YML format.
     *
     * @param format         The file format ("json" or "yml").
     * @param fileLong1      The first input file.
     * @param fileLong2      The second input file.
     * @param expectedResult The expected result variable name.
     */
    @ParameterizedTest
    @CsvSource({"json, fileLong1.json, fileLong2.json, expectedLongStylishJson",
        "yml, fileLong1.yml, fileLong2.yml, expectedLongStylishYml"})
    public void testLongFormats(String format, String fileLong1, String fileLong2, String expectedResult)
            throws Exception {
        String actual = Differ.generate(getResourcePath(fileLong1), getResourcePath(fileLong2));
        assertEquals(actual, format.equals("json") ? expectedLongJson : expectedLongYml);
    }

    /**
     * Checks if an IllegalArgumentException is thrown if both files are empty.
     *
     * @param format The file format ("json" or "yml").
     * @param file1  The first input file.
     * @param file2  The second input file.
     */
    @ParameterizedTest
    @CsvSource({"json, empty1.json, empty2.json", "yml, empty1.yml, empty2.yml"})
    public void testBothEmpty(String format, String file1, String file2) {
        assertThrows(IllegalArgumentException.class, () -> Differ.generate(getResourcePath(file1),
                getResourcePath(file2)));
    }

    /**
     * Checks if an IllegalArgumentException is thrown if one of the files is empty.
     *
     * @param format    The file format ("json" or "yml").
     * @param emptyFile The empty input file.
     * @param file1     The first input file.
     * @param file2     The second input file.
     * @param file3     The third input file.
     */
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
