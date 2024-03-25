package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("도메인 LottoNumbers 테스트")
class LottoNumbersTest {

    private Lottos lottos;

    @BeforeEach
    void initComputer() {
        Lotto lotto1 = Lotto.ofNumbers(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto2 = Lotto.ofNumbers(List.of(1, 2, 4, 5, 6, 7));
        Lotto lotto3 = Lotto.ofNumbers(List.of(1, 2, 5, 6, 7, 8));

        lottos = new Lottos(List.of(lotto1, lotto2, lotto3));
    }
    @Test
    void issueNumbers() {

    }

    @DisplayName("로또 번호 생성 범위 1~45 테스트")
    @Test
    void lottoGenerate_range() {
        List<LottoNumber> expected = IntStream.range(1, 46).boxed().map(LottoNumber::new).collect(Collectors.toList());
        List<LottoNumber> result = LottoNumbers.issueNumbers();

        assertThat(expected).containsAll(result);
    }

    @DisplayName("로또 번호 원자성 테스트")
    @Test
    void lottoGenerate_atomic_test() {
        List<LottoNumber> result = LottoNumbers.issueNumbers();
        Set<LottoNumber> resultSet = result.stream().collect(Collectors.toSet());
        assertThat(resultSet.size()).isEqualTo(result.size());
    }

    @DisplayName("로또 번호 정렬 검증")
    @Test
    void lottoGenerate_sort_test() {
        List<LottoNumber> result = LottoNumbers.issueNumbers();

        Collections.sort(result);
        List<LottoNumber> expected = new ArrayList<>(result);

        assertThat(result).isEqualTo(expected);
    }

}
