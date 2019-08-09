import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InstructionCreationFromOneTokenTests {

    private Instruction instruction;

    @BeforeEach
    void setup() {
        instruction = new Instruction("oPeN");
    }

    @Test
    @DisplayName("an instruction made from a single token should have that token capitalized as its command()")
    void instruction_made_from_single_token_1() {

        assertThat(instruction.command()).isEqualTo("OPEN");

    }

    @Test
    @DisplayName("an instruction made from a single token should have an empty string as its primaryArgument()")
    void instruction_made_from_single_token_2() {

        assertThat(instruction.primaryArgument()).isEqualTo("");

    }

    @Test
    @DisplayName("an instruction made from a single token should have an empty string as its secondaryArguments()")
    void instruction_made_from_single_token_3() {

        assertThat(instruction.secondaryArguments()).isEqualTo("");
    }

}
