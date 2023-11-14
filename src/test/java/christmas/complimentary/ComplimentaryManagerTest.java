package christmas.complimentary;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import christmas.domain.ComplimentaryManager;

class ComplimentaryManagerTest {

    private final ComplimentaryManager complimentaryManager = new ComplimentaryManager();

    @Test
    void 높은_가격_사은품_테스트() {
        String complimentaryItems = complimentaryManager.getComplimentary(120000);
        assertThat(complimentaryItems).isEqualTo("샴페인 1개\n");
    }

    @Test
    void 낮은_가격_사은품_없음_테스트() {
        String complimentaryItems = complimentaryManager.getComplimentary(50000);
        assertThat(complimentaryItems).isEqualTo("없음\n");
    }
}