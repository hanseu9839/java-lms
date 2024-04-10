package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;

class PaidSessionTypeTest {
    @Nested
    @DisplayName("PaidSessionType 생성 테스트")
    class InstanceCreationTest {
        @ParameterizedTest
        @CsvSource(value = {"-1:800000", "80:-1", "0:0"}, delimiter = ':')
        @DisplayName("maxNumberOfEnrollment 또는 fee가 0보다 작거나 같은 경우 IllegalArgumentException이 발생한다.")
        void testFailCase(int maxNumberOfEnrollment, int fee) {
            assertThatThrownBy(() -> new PaidSessionType(maxNumberOfEnrollment, fee))
                    .isExactlyInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("검증 조건을 통과한 경우 PaidSessionType을 생성한다.")
        void testSuccessCase() {
            assertThatNoException().isThrownBy(() -> new PaidSessionType(80, 800000));
        }
    }

    @Test
    @DisplayName("isSessionFull(): currentNumberOfEnrollment < maxNumberOfEnrollment 이면 true를 그렇지 않은 경우 false를 반환한다.")
    void testIsSessionFull() {
        PaidSessionType paidSessionType = new PaidSessionType(2, 100);

        assertThat(paidSessionType.isSessionFull(1)).isTrue();
        assertThat(paidSessionType.isSessionFull(2)).isFalse();
    }

    @Test
    @DisplayName("isValidPayment(): Payment.amount가 fee와 동일한 경우 true 그렇지 않은 경우 false를 반환한다.")
    void testIsValidPayment() {
        Payment payment = new Payment();
        PaidSessionType paidSessionType = new PaidSessionType(2, 100);

        assertThat(paidSessionType.isValidPayment(payment)).isFalse();
    }
}