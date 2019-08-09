import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InstructionCreationFromThreeTokensTests {

    private Instruction instruction;

    @BeforeEach
    void setup() {
        instruction = new Instruction("CLick tHAt cat");
    }

    @Test
    @DisplayName("an instruction made from three tokens should have the first token capitalized as its command()")
    void instruction_made_from_three_tokens_1() {

        assertThat(instruction.command()).isEqualTo("CLICK");

    }

    @Test
    @DisplayName("an instruction made from three tokens should have the second token as its primaryArgument()")
    void instruction_made_from_three_tokens_2() {

        assertThat(instruction.primaryArgument()).isEqualTo("tHAt");

    }

    @Test
    @DisplayName("an instruction made from three tokens should have the third token as its secondaryArguments()")
    void instruction_made_from_three_tokens_3() {

        assertThat(instruction.secondaryArguments()).isEqualTo("cat");
    }

}
