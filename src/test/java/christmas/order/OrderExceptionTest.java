package christmas.order;

import camp.nextstep.edu.missionutils.test.NsTest;
import christmas.Application;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

class OrderExceptionTest extends NsTest {

    @Test
    void 메뉴가_중복_시_예외_테스트() {
        assertSimpleTest(() -> {
            runException("3", "크리스마스파스타-1,티본스테이크-2,크리스마스파스타-1");
            assertThat(output()).contains("[ERROR] 중복된 메뉴 항목이 있습니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 음료만_주문_시_예외_테스트() {
        assertSimpleTest(() -> {
            runException("3", "제로콜라-2");
            assertThat(output()).contains("[ERROR] 음료만 주문 시, 주문할 수 없습니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 메뉴가_20_개_초과_시_예외_테스트() {
        assertSimpleTest(() -> {
            runException("3", "제로콜라-2,티본스테이크-15,크리스마스파스타-15");
            assertThat(output()).contains("[ERROR] 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 메뉴의_개수가_1개_미만일_때_예외_테스트() {
        assertSimpleTest(() -> {
            runException("3", "티본스테이크-0,크리스마스파스타-0");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 존재하지_않는_메뉴_입력_시_예외_테스트() {
        assertSimpleTest(() -> {
            runException("3", "후라이드치킨-5");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
