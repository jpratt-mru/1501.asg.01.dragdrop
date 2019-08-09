import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserInterfaceStringFromUserTests {

    private static final String PROMPT = "input > ";
    private static final String EOLN = String.format("%n");
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Test
    @DisplayName("provides a prompt without an EOLN at the end")
    void provides_a_prompt_without_an_eoln_at_the_end() {
        String userInput = EOLN;
        InputStream inStream = new ByteArrayInputStream(userInput.getBytes());
        PrintStream outStream = new PrintStream(outContent);

        UserInterface ui = new UserInterface(inStream, outStream);
        ui.stringFromUser(PROMPT);

        String textOnDisplay = outContent.toString();

        assertThat(textOnDisplay).doesNotContain(PROMPT + EOLN);
    }

    @Test
    @DisplayName("returns user input as-is if user enters no leading or trailing whitespace")
    void returns_user_input_as_is_if_no_leading_trailing_whitespace() {
        String userInput = "*That* was certainly interesting...";
        InputStream inStream = new ByteArrayInputStream(userInput.getBytes());
        PrintStream outStream = new PrintStream(outContent);

        UserInterface ui = new UserInterface(inStream, outStream);
        String receivedString = ui.stringFromUser(PROMPT);

        assertThat(receivedString).isEqualTo(userInput);
    }

    @Test
    @DisplayName("returns user input without leading or trailing whitespace if user enters leading or trailing whitespace")
    void returns_user_input_stripped_of_leading_trailing_whitespace() {
        String userInput = "  I     thought \t\t \tyou said WINNEBAGO! ";
        InputStream inStream = new ByteArrayInputStream(userInput.getBytes());
        PrintStream outStream = new PrintStream(outContent);

        UserInterface ui = new UserInterface(inStream, outStream);
        String receivedString = ui.stringFromUser(PROMPT);

        assertThat(receivedString).isEqualTo(userInput.trim());
    }

    @Test
    @DisplayName("an empty string is returned if ONLY whitespace is entered by user")
    void returns_empty_string_if_only_whitespace_entered() {
        String userInput = "  \t      \t     \t";
        InputStream inStream = new ByteArrayInputStream(userInput.getBytes());
        PrintStream outStream = new PrintStream(outContent);

        UserInterface ui = new UserInterface(inStream, outStream);
        String receivedString = ui.stringFromUser(PROMPT);

        assertThat(receivedString).isEqualTo("");
    }

}
