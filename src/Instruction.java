import java.util.Scanner;

public class Instruction {

    private String command = "";
    private String primaryArgument = "";
    private String secondaryArguments = "";

    public Instruction(String instructionText) {
        extractTextIntoCommandAndArgs(instructionText);
    }

    private void extractTextIntoCommandAndArgs(String instructionText) {

        Scanner instructionScanner = new Scanner(instructionText);
        if (instructionScanner.hasNext()) {
            command = instructionScanner.next().toUpperCase().trim();
        }
        if (instructionScanner.hasNext()) {
            primaryArgument = instructionScanner.next().trim();
        }
        if (instructionScanner.hasNext()) {
            secondaryArguments = instructionScanner.nextLine().trim();
            secondaryArguments = singleSpaceifyTokens(secondaryArguments);
        }
        instructionScanner.close();
    }

    private String singleSpaceifyTokens(String s) {
        Scanner scanner = new Scanner(s);

        String result = scanner.next();

        while (scanner.hasNext()) {
            result += " " + scanner.next();
        }

        scanner.close();

        return result;
    }

    public String command() {
        return command;
    }

    public String primaryArgument() {
        return primaryArgument;
    }

    public String secondaryArguments() {
        return secondaryArguments;
    }

}
