package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.util.List;

public class SessionType {

    private PayType type;
    private Long price;
    private Integer capacity;

    public SessionType() {
        this.type = PayType.FREE;
    }

    public SessionType(PayType type, Long price, Integer capacity) {
        this.type = type;
        this.price = price;
        this.capacity = capacity;
    }

    public boolean isMaxCapacity(List<NsUser> userList) {
        if (PayType.FREE.equals(type)) {
            return true;
        }
        return capacity < userList.size();
    }

    public boolean isEqualPrice(Payment payment) {
        return payment.isEqualPrice(price);
    }
}
