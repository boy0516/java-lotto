package lotto.services;

import lotto.models.IssuedLotto;
import lotto.models.LottoStatistics;
import lotto.models.WinningLotto;
import lotto.models.enums.IssueType;
import lotto.models.enums.Rank;
import lotto.models.request.IssueLottoRequest;
import lotto.models.request.WinningLottoRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoStatisticsServiceTest {

    private static final LottoStatisticsService lottoStatisticsService = new LottoStatisticsService();
    private static final List<IssuedLotto> lottos = new ArrayList<>();
    private static final String WINNING_NUMBER = "1, 2, 3, 4, 5, 6";
    private static final List<IssuedLotto> duplicatedLottos = new ArrayList<>();
    private static final int PAYMENT = 3500;
    private static WinningLotto winningLotto;

    @BeforeAll
    static void setLotto() {
        lottos.add(IssuedLotto.of(List.of(1, 2, 3, 4, 5, 6), IssueType.MANUAL));
        lottos.add(IssuedLotto.of(List.of(1, 2, 3, 7, 8, 9), IssueType.MANUAL));
        lottos.add(IssuedLotto.of(List.of(11, 12, 13, 14, 15, 16), IssueType.MANUAL));
    }

    @BeforeAll
    static void setDuplicatedLottos() {
        duplicatedLottos.add(IssuedLotto.of(List.of(1, 2, 3, 4, 5, 6), IssueType.MANUAL));
        duplicatedLottos.add(IssuedLotto.of(List.of(1, 2, 3, 4, 5, 6), IssueType.MANUAL));
        duplicatedLottos.add(IssuedLotto.of(List.of(1, 2, 3, 4, 5, 6), IssueType.MANUAL));
    }

    @BeforeAll
    static void setWinningLotto() {
        winningLotto = WinningLotto.from(WinningLottoRequest.of(WINNING_NUMBER, 10));
    }

    @Test
    @DisplayName("당첨번호와 로또번호 리스트를 받으면 각 Rank가 몇 개씩 당첨됐는지 가져온다.")
    void createStatistics() {
        List<LottoStatistics> lottoStatistics = lottoStatisticsService.getLottoStatistics(lottos, winningLotto);

        lottoStatistics.forEach(statistics -> {
            if (statistics.getRank().equals(Rank.FIRST)) {
                assertThat(statistics.getCount()).isEqualTo(1);
            } else if (statistics.getRank().equals(Rank.FIFTH)) {
                assertThat(statistics.getCount()).isEqualTo(1);
            } else if (statistics.getRank().equals(Rank.OTHER)) {
                assertThat(statistics.getCount()).isEqualTo(1);
            }
        });
    }

    @Test
    @DisplayName("6개 모두 당첨된 복권 3개가 발행된 경우 Rank.FIRST의 count가 3이다.")
    void createStatistics2() {
        List<LottoStatistics> lottoStatistics = lottoStatisticsService.getLottoStatistics(duplicatedLottos, winningLotto);

        lottoStatistics.forEach(statistics -> {
            if (statistics.getRank().equals(Rank.FIRST)) {
                assertThat(statistics.getCount()).isEqualTo(3);
            }
        });
    }

    @Test
    @DisplayName("통계치와 지불한 금액을 전달하면 이율을 리턴한다.")
    void getRevenueRatio() {
        List<LottoStatistics> lottoStatistics = lottoStatisticsService.getLottoStatistics(lottos, winningLotto);
        long totalAmount = lottoStatistics.stream()
                .filter(statistics -> statistics.getCount() > 0)
                .map(statistics -> statistics.getRank().getAmount())
                .mapToLong(Long::longValue)
                .sum();

        assertThat(lottoStatisticsService.getRevenueRatio(lottoStatistics, IssueLottoRequest.of(PAYMENT, new ArrayList<>()))).isEqualTo(totalAmount / (PAYMENT / 1000f * 1000));
    }

}
