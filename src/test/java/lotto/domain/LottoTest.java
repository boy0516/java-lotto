package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@DisplayName("도메인 Lotto 테스트")
class LottoTest {

    @DisplayName("Lotto 생성자 빈값 예외 테스트")
    @ParameterizedTest
    @NullAndEmptySource
    void validEmpty(List<Integer> input) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Lotto.ofNumbers(input))
                .withMessageMatching("입력값이 없습니다");
    }

    @DisplayName("Size 편의 메서드 테스트")
    @Test
    void size() {
        Lotto givenLotto = Lotto.ofNumbers(List.of(1, 2, 3, 4, 5, 6));
        int result = givenLotto.size();
        assertThat(result).isEqualTo(6);
    }

}
