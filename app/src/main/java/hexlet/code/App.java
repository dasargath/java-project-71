package hexlet.code;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;


@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two different files and outputs the difference.")
public class App implements Callable<Integer> {

    private static final int SUCCESS_EXIT = 0;
    private static final int ERROR_EXIT = 1;
    @Parameters(index = "0", description = "path to first file.")
    private static String filePath1;
    @Parameters(index = "1", description = "path to second file.")
    private static String filePath2;
    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    private String formatName = "stylish";
    public static void main(String... args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
    @Override
    public Integer call() throws Exception {
        Differ differ = new Differ();
        try {
            System.out.println(differ.generate(filePath1, filePath2, formatName));
            return SUCCESS_EXIT;
        } catch (Exception e) {
            return ERROR_EXIT;
        }
    }
}
