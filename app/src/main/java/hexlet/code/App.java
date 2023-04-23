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
    private String filePath1;

    @Parameters(index = "1", description = "path to second file.")
    private String filePath2;

    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    private String formatName = "stylish";

    public static void main(String... args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() {
        try {
            System.out.println(Differ.generate(filePath1, filePath2, formatName));
            return SUCCESS_OUTPUT;
        } catch (Exception e) {
            return ERROR_OUTPUT;
        }
    }
}
