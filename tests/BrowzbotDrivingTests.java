import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BrowzbotDrivingTests {

    Browzbot bot;

    @BeforeEach
    void setup() {
        bot = new Browzbot("chrome");
    }

    void playAndOutputToConsole(String instructionText) {
        Result result = bot.run(new Instruction(instructionText));
        System.out.format("%s => %d: %s%n", instructionText, result.exitStatus(), result.msg());
    }

    @Test
    void youtube_test() {
        playAndOutputToConsole("open youtube.com");
        playAndOutputToConsole("fill search stop making sense crosseyed and painless");
        playAndOutputToConsole("click id search-icon-legacy");
        playAndOutputToConsole("wait 1");
        playAndOutputToConsole("click link Talking Heads - Crosseyed and Painless LIVE (Stop Making Sense)");
        playAndOutputToConsole("wait 2");
        playAndOutputToConsole("shoot 1_youtube_screenshot_1.png");
        playAndOutputToConsole("back");
        playAndOutputToConsole("shoot 2_youtube_screenshot_2.png");
        playAndOutputToConsole("stop");
    }

    @Test
    void donjon_test() {
        playAndOutputToConsole("open donjon.bin.sh/5e/spells");
        playAndOutputToConsole("select class Druid");
        playAndOutputToConsole("select concentration yes");
        playAndOutputToConsole("click class span sort_icon");
        playAndOutputToConsole("click class span sort_icon");
        playAndOutputToConsole("shoot 3_donjon_screenshot.png");
        playAndOutputToConsole("stop");
    }

    @Test
    void aircanada_test() {
        playAndOutputToConsole(
                "open www.aircanada.com/ca/en/aco/home/fly/flight-information/flight-status-results.html");
        playAndOutputToConsole("fill status_by_number_flight 10");
        playAndOutputToConsole("click id btnStatusByNumberSearch");
        playAndOutputToConsole("wait 4");
        playAndOutputToConsole("shoot 4_aircanada_screenshot.png");
        playAndOutputToConsole("stop");
    }

}
