import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BrowzbotRunsInstructionWithWrongNumberOfArgsTests {

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
    @DisplayName("running an instruction that has a known command that takes no arguments, but there is one argument returns a result with the correct exit status and message")
    void known_zero_arg_instruction_with_one_arg() {

        Result result = runInstruction("stop whining");

        assertThat(result.exitStatus()).isEqualTo(2);
        assertThat(result.msg()).isEqualTo("wrong number of arguments for command STOP");

    }

    @Test
    @DisplayName("running an instruction that has a known command that takes no arguments, but there are two arguments returns a result with the correct exit status and message")
    void known_zero_arg_instruction_with_two_args() {

        Result result = runInstruction("forward you dogs");

        assertThat(result.exitStatus()).isEqualTo(2);
        assertThat(result.msg()).isEqualTo("wrong number of arguments for command FORWARD");

    }

    @Test
    @DisplayName("running an instruction that has a known command that takes one argument, but there are zero arguments returns a result with the correct exit status and message")
    void known_one_arg_instruction_with_zero_args() {

        Result result = runInstruction("open");

        assertThat(result.exitStatus()).isEqualTo(2);
        assertThat(result.msg()).isEqualTo("wrong number of arguments for command OPEN");

    }

    @Test
    @DisplayName("running an instruction that has a known command that takes one argument, but there are two arguments returns a result with the correct exit status and message")
    void known_one_arg_instruction_with_two_args() {

        Result result = runInstruction("open the pod bay doors Hal");

        assertThat(result.exitStatus()).isEqualTo(2);
        assertThat(result.msg()).isEqualTo("wrong number of arguments for command OPEN");

    }

    @Test
    @DisplayName("running an instruction that has a known command that takes two arguments, but there are no arguments returns a result with the correct exit status and message")
    void known_two_arg_instruction_with_zero_args() {

        Result result = runInstruction("click");

        assertThat(result.exitStatus()).isEqualTo(2);
        assertThat(result.msg()).isEqualTo("wrong number of arguments for command CLICK");

    }

    @Test
    @DisplayName("running an instruction that has a known command that takes two arguments, but there is only one argument returns a result with the correct exit status and message")
    void known_two_arg_instruction_with_only_one_arg() {

        Result result = runInstruction("click id");

        assertThat(result.exitStatus()).isEqualTo(2);
        assertThat(result.msg()).isEqualTo("wrong number of arguments for command CLICK");

    }
}
