package nextstep.lms.domain;

import java.time.LocalDateTime;

public class SessionPeriod {
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    public SessionPeriod(LocalDateTime startDate, LocalDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void canEnrollCheck(LocalDateTime now) {
        if (now.isAfter(startDate)) {
            throw new IllegalArgumentException("강의 시작 후에는 수강신청할 수 없습니다.");
        }
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }
}