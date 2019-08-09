import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BrowzbotRunsInstructionSuccessfullyTests {

    private Browzbot browzbot;

    @BeforeEach
    void setup() {
        browzbot = new Browzbot();
    }

    Result runInstruction(String userInput) {
        Instruction instruction = new Instruction(userInput);

        return browzbot.run(instruction);
    }

    @Test
    @DisplayName("stop instruction")
    void stop_instruction() {

        Result result = runInstruction("stop");

        assertThat(result.exitStatus()).isEqualTo(0);
        assertThat(result.msg()).isEqualTo("OK");

    }

    @Test
    @DisplayName("open instruction")
    void open_instruction() {

        Result result = runInstruction("open www.boardgamegeek.com");

        assertThat(result.exitStatus()).isEqualTo(0);
        assertThat(result.msg()).isEqualTo("OK");

    }

    @Test
    @DisplayName("shoot instruction")
    void shoot_instruction() {

        Result result = runInstruction("shoot somepage.png");

        assertThat(result.exitStatus()).isEqualTo(0);
        assertThat(result.msg()).isEqualTo("OK");

    }

    @Test
    @DisplayName("wait instruction")
    void wait_instruction() {

        Result result = runInstruction("wait 3");

        assertThat(result.exitStatus()).isEqualTo(0);
        assertThat(result.msg()).isEqualTo("OK");

    }

    @Test
    @DisplayName("back instruction")
    void back_instruction() {

        Result result = runInstruction("back");

        assertThat(result.exitStatus()).isEqualTo(0);
        assertThat(result.msg()).isEqualTo("OK");

    }

    @Test
    @DisplayName("forward instruction")
    void forward_instruction() {

        Result result = runInstruction("forward");

        assertThat(result.exitStatus()).isEqualTo(0);
        assertThat(result.msg()).isEqualTo("OK");

    }

    @Test
    @DisplayName("click link instruction")
    void click_link_instruction() {

        Result result = runInstruction("click link this way to the exit");

        assertThat(result.exitStatus()).isEqualTo(0);
        assertThat(result.msg()).isEqualTo("OK");

    }

    @Test
    @DisplayName("click id instruction")
    void click_id_instruction() {

        Result result = runInstruction("click id search-bar");

        assertThat(result.exitStatus()).isEqualTo(0);
        assertThat(result.msg()).isEqualTo("OK");

    }

    @Test
    @DisplayName("click class instruction")
    void click_class_instruction() {

        Result result = runInstruction("click class button highlighted important");

        assertThat(result.exitStatus()).isEqualTo(0);
        assertThat(result.msg()).isEqualTo("OK");

    }

    @Test
    @DisplayName("fill instruction")
    void fill_instruction() {

        Result result = runInstruction("fill name Mr. Ed");

        assertThat(result.exitStatus()).isEqualTo(0);
        assertThat(result.msg()).isEqualTo("OK");

    }

}
