package hexlet.code;

import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class TestDiffer {
    private final String filePathJson1 = ("src/test/resources/file1.json");
    private final String filePathJson2 = ("src/test/resources/file2.json");
    private final String filePathYml1 = ("src/test/resources/file1.yml");
    private final String filePathYml2 = ("src/test/resources/file2.yml");
    private final String filePathEmptyJson1 = ("src/test/resources/empty1.json");
    private final String filePathEmptyJson2 = ("src/test/resources/empty2.json");
    private final String filePathEmptyYml1 = ("src/test/resources/empty1.yml");
    private final String filePathEmptyYml2 = ("src/test/resources/empty2.yml");

    @Test
    public void testBothEmptyJson() {
        assertThrows(IllegalArgumentException.class, () -> Differ.generate(filePathEmptyJson1, filePathEmptyJson2));
    }
    @Test
    public void testBothEmptyYml() {
        assertThrows(IllegalArgumentException.class, () -> Differ.generate(filePathEmptyYml1, filePathEmptyYml2));
    }

    @Test
    public void testOneEmptyJson() {
        assertThrows(IllegalArgumentException.class, () -> Differ.generate(filePathEmptyJson1, filePathJson2));
        assertThrows(IllegalArgumentException.class, () -> Differ.generate(filePathJson1, filePathEmptyJson1));
    }

    @Test
    public void testOneEmptyYml() {
        assertThrows(IllegalArgumentException.class, () -> Differ.generate(filePathEmptyYml1, filePathYml2));
        assertThrows(IllegalArgumentException.class, () -> Differ.generate(filePathYml1, filePathEmptyYml1));
    }

    @Test
    public void testDefaultJson() throws Exception {
        String actual = Differ.generate(filePathJson1, filePathJson2);
        String expected = Files.readString(Paths.get("src/test/resources/expectedJson.json"));
        assertEquals(actual, expected);
    }

    @Test
    public void testDefaultYml() throws Exception {
        String actual = Differ.generate(filePathYml1, filePathYml2);
        String expected = Files.readString(Paths.get("src/test/resources/expectedYml"));
        assertEquals(actual, expected);
    }

    @Test
    public void testJsonLong() throws Exception {
        String filepath3Json = ("src/test/resources/fileLong1.json");
        String filepath4Json = ("src/test/resources/fileLong2.json");
        String expected = Files.readString(Paths.get("src/test/resources/expectedLongStylishJson"));
        String actual = Differ.generate(filepath3Json, filepath4Json);
        assertEquals(actual, expected);
    }

    @Test
    public void testYmlLong() throws Exception {
        String filepath3Yml = ("src/test/resources/fileLong1.yml");
        String filepath4Yml = ("src/test/resources/fileLong2.yml");
        String actual = Differ.generate(filepath3Yml, filepath4Yml);
        String expected = Files.readString(Paths.get("src/test/resources/expectedLongStylishYml"));
        assertEquals(actual, expected);
    }

    @Test
    public void testJsonToPlain() throws Exception {
        String filepath3Json = ("src/test/resources/fileLong1.json");
        String filepath4Json = ("src/test/resources/fileLong2.json");
        String actual = Differ.generate(filepath3Json, filepath4Json, "plain");
        String expected = Files.readString(Paths.get("src/test/resources/plain"));
        assertEquals(actual, expected);
    }

    @Test
    public void testYmlToPlain() throws Exception {
        String filepath3Yml = ("src/test/resources/fileLong1.yml");
        String filepath4Yml = ("src/test/resources/fileLong2.yml");
        String actual = Differ.generate(filepath3Yml, filepath4Yml, "plain");
        String expected = Files.readString(Paths.get("src/test/resources/plain"));
        assertEquals(actual, expected);
    }

    @Test
    public void testJsonToJson() throws Exception {
        String actual = Differ.generate(filePathJson1, filePathJson2, "json");
        String expected = Files.readString(Paths.get("src/test/resources/json"));
        assertEquals(actual, expected);
    }

    @Test
    public void testYmlToJson() throws Exception {
        String actual = Differ.generate(filePathYml1, filePathYml2, "json");
        String expected = Files.readString(Paths.get("src/test/resources/json"));
        assertEquals(actual, expected);
    }

    @Test
    public void testStylishJson() throws Exception {
        String actual = Differ.generate(filePathJson1, filePathJson2, "stylish");
        String expected = Files.readString(Paths.get("src/test/resources/expectedJson.json"));
        assertEquals(actual, expected);
    }

    @Test
    public void testStylishYml() throws Exception {
        String actual = Differ.generate(filePathYml1, filePathYml2, "stylish");
        String expected = Files.readString(Paths.get("src/test/resources/expectedYml"));
        assertEquals(actual, expected);
    }
}
