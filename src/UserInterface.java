import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class UserInterface {

    private final PrintStream outputStream;
    private final Scanner scanner;

    public UserInterface() {
        this(System.in, System.out);
    }

    public UserInterface(InputStream inputStream, PrintStream outputStream) {
        this.outputStream = outputStream;
        scanner = new Scanner(inputStream);
    }

    public void println(String s) {
        outputStream.println(s);
    }

    public void print(String s) {
        outputStream.print(s);
    }

    public String stringFromUser(String prompt) {
        print(prompt);
        return scanner.nextLine().trim();
    }

}
