import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultSimpleTests {

    @Test
    @DisplayName("constructed result has exitStatus and msg properly set")
    void constructed_result_has_exitStatus_and_msg_properly_set() {

        Result result = new Result(15, "she can't take anymore, cap'n!");

        assertThat(result.exitStatus()).isEqualTo(15);
        assertThat(result.msg()).isEqualTo("she can't take anymore, cap'n!");

    }

}
