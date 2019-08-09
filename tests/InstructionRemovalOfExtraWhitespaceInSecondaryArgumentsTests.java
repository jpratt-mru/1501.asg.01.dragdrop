import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InstructionRemovalOfExtraWhitespaceInSecondaryArgumentsTests {

    @Test
    @DisplayName("secondaryArguments() returns a string with tokens separated by a single space")
    void secondaryArguments_returns_string_with_tokens_separated_by_single_space() {
        Instruction instruction = new Instruction("take the road\t \tless     \t  \t traveled");

        assertThat(instruction.secondaryArguments()).isEqualTo("road less traveled");

    }

}
