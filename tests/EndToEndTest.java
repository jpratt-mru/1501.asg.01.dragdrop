import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

class EndToEndTest {

    private static final String EOLN = String.format("%n");

    @Test
    void test() {
        String userInput = "firefox" + EOLN +
                "open boardgamegeek.com" + EOLN +
                "open imdb.com" + EOLN +
                "back" + EOLN +
                "forward" + EOLN +
                "fill navbar-query aliens" + EOLN +
                "click class div magnifyingglass navbarSprite" + EOLN +
                "click link Aliens" + EOLN +
                "shoot end-to-end.png" + EOLN +
                "stop" + EOLN;

        InputStream inStream = new ByteArrayInputStream(userInput.getBytes());
        PrintStream outStream = System.out;

        UserInterface ui = new UserInterface(inStream, outStream);

        new BrowserAutomationAsg(ui).run();
    }

}
