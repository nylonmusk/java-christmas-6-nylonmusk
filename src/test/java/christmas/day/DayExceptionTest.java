package christmas.day;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import christmas.Application;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DayExceptionTest extends NsTest {

    @ParameterizedTest
    @ValueSource(strings = {"a", "!", "2023/12/32", "December 25"})
    void 숫자가_아닌_날짜_예외_테스트(String day) {
        assertSimpleTest(() -> {
            runException(day);
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "32"})
    void 일수_경계_예외_테스트(String day) {
        assertSimpleTest(() -> {
            runException(day);
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
