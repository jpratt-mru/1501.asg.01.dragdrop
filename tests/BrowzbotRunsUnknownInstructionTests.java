import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BrowzbotRunsUnknownInstructionTests {

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
    @DisplayName("running an empty instruction returns a result with the correct exit status and message")
    void empty_instruction() {

        Result result = runInstruction("");

        assertThat(result.exitStatus()).isEqualTo(1);
        assertThat(result.msg()).isEqualTo("empty instruction");

    }

    @Test
    @DisplayName("running an instruction with only an unknown command part returns a result with the correct exit status and message")
    void only_unknown_command_part_instruction() {

        Result result = runInstruction("woof");

        assertThat(result.exitStatus()).isEqualTo(1);
        assertThat(result.msg()).isEqualTo("unknown command WOOF");

    }

    @Test
    @DisplayName("running an instruction with an unknown command part and a primary argument returns a result with the correct exit status and message")
    void unknown_command_part_and_primary_argument_instruction() {

        Result result = runInstruction("bang head");

        assertThat(result.exitStatus()).isEqualTo(1);
        assertThat(result.msg()).isEqualTo("unknown command BANG");

    }

    @Test
    @DisplayName("running an instruction with an unknown command part and multiple arguments returns a result with the correct exit status and message")
    void unknown_command_part_and_multiple_arguments_instruction() {

        Result result = runInstruction("remember to double tap");

        assertThat(result.exitStatus()).isEqualTo(1);
        assertThat(result.msg()).isEqualTo("unknown command REMEMBER");

    }

}
