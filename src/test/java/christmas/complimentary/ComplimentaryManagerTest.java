package christmas.complimentary;

import christmas.domain.ComplimentaryManager;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ComplimentaryManagerTest {

    private ComplimentaryManager complimentaryManager = new ComplimentaryManager();

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