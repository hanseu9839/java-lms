package nextstep.payments.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentsTest {

    @Test
    void 지불_추가_테스트() {
        Payments payments = new Payments();

        payments.addPayments(new Payment("payment1", 1L, 1L, 1000L));
        assertThat(payments.getPayments()).isEqualTo(List.of(new Payment("payment1", 1L, 1L, 1000L)));
    }

}