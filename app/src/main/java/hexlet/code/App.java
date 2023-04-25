package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two different files and outputs the difference.")
public class App implements Callable<Integer> {

    private static final int SUCCESS_OUTPUT = 0;
    private static final int ERROR_OUTPUT = 1;

    @Parameters(index = "0", description = "path to first file.")
    private String filepath1;

    @Parameters(index = "1", description = "path to second file.")
    private String filepath2;

    @Option(names = {"-f", "--format"}, defaultValue = "stylish", description = "output format")
    private String format = "stylish";

    public static void main(String... args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    /**
     * Executes the gendiff comparison operation. Returns SUCCESS_OUTPUT if successful, ERROR_OUTPUT otherwise.
     * Subclasses can override this method for a custom implementation.
     */

    @Override
    public Integer call() throws Exception {
        try {
            System.out.println(Differ.generate(filepath1, filepath2, format));
            return SUCCESS_OUTPUT;
        } catch (Exception e) {
            System.out.println("File " + e.getMessage() + " can not be found.");
            return ERROR_OUTPUT;
        }
    }
}
