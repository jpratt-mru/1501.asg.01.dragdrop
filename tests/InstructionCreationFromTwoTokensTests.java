import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InstructionCreationFromTwoTokensTests {

    private Instruction instruction;

    @BeforeEach
    void setup() {
        instruction = new Instruction("clIcK YonDER");
    }

    @Test
    @DisplayName("an instruction made from two tokens should have the first token capitalized as its command()")
    void instruction_made_from_two_tokens_1() {

        assertThat(instruction.command()).isEqualTo("CLICK");

    }

    @Test
    @DisplayName("an instruction made from two tokens should have the second token as its primaryArgument()")
    void instruction_made_from_two_tokens_2() {

        assertThat(instruction.primaryArgument()).isEqualTo("YonDER");

    }

    @Test
    @DisplayName("an instruction made from a single token should have an empty string as its secondaryArguments()")
    void instruction_made_from_two_tokens_3() {

        assertThat(instruction.secondaryArguments()).isEqualTo("");
    }

}
