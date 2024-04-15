package nextstep.session.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DurationTest {

    @DisplayName("시작일자가 종료일자보다 미래일 수 없다.")
    @Test
    void throwIllegalArgumentExceptionWhenStartDateIsLaterThanEndDate() {
        // given
        LocalDateTime startDate = LocalDateTime.now().plusDays(5);
        LocalDateTime endDate = LocalDateTime.now().plusDays(1);

        // then
        assertThatThrownBy(() -> new Duration(startDate, endDate))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("조회하는 일자가 시작일자와 종료일자 사이인지 조회한다.")
    @Test
    void isAvailable() {
        // given
        LocalDateTime startDate = LocalDateTime.now().plusDays(10);
        LocalDateTime endDate = LocalDateTime.now().plusDays(20);
        LocalDateTime queryDateFit = LocalDateTime.now().plusDays(11);
        LocalDateTime queryDateLater = LocalDateTime.now().plusDays(22);

        // when
        Duration duration = new Duration(startDate, endDate);

        // then
        assertThat(duration.isAvailable(queryDateFit)).isTrue();
        assertThat(duration.isAvailable(queryDateLater)).isFalse();
    }

    @DisplayName("시작일자가 현재보다 과거일 수 없다.")
    @Test
    void throwIllegalArgumentExceptionWhenStartDateIsPast() {
        // given
        LocalDateTime startDate = LocalDateTime.now().minusDays(5);;
        LocalDateTime endDate = LocalDateTime.now().plusDays(5);

        // then
        assertThatThrownBy(() -> new Duration(startDate, endDate))
                .isInstanceOf(IllegalArgumentException.class);
    }
}