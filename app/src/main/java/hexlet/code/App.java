package hexlet.code;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;


@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two different files and outputs the difference.")
public class App implements Runnable {

    @Parameters(index = "0", description = "path to first file.")
    private static String filePath1;
    @Parameters(index = "1", description = "path to second file.")
    private static String filePath2;
    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    private String format = "stylish";
    public static void main(String... args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
    @Override
    public void run() {
        Differ differ = new Differ();
        differ.generate(filePath1, filePath2, format);
    }
}
