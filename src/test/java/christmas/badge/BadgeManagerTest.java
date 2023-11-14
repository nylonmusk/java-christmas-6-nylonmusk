package christmas.badge;

import christmas.domain.BadgeManager;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BadgeManagerTest {

    private BadgeManager badgeManager = new BadgeManager();

    private static Stream<Arguments> badgeData() {
        return Stream.of(
                Arguments.of(20000, "산타"),
                Arguments.of(10000, "트리"),
                Arguments.of(5000, "별"),
                Arguments.of(0, "없음")
        );
    }

    @ParameterizedTest
    @MethodSource("badgeData")
    void 뱃지_테스트(int discountAmount, String expectedBadge) {
        String badge = badgeManager.getBadge(discountAmount);
        assertThat(badge).isEqualTo(expectedBadge);
    }
}