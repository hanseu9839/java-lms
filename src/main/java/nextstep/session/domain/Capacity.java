package nextstep.session.domain;

import nextstep.exception.CapacityException;

public class Capacity {

    public static final int NO_CAPACITY = 0;
    public static final int A_STUDENT = 1;
    private final int maxCapacity;
    private int enrolled = 0;

    public static Capacity create(int maxCapacity) {
        return new Capacity(maxCapacity);
    }

    public static Capacity create(int maxCapacity, int enrolled) {
        return new Capacity(maxCapacity, enrolled);
    }

    private Capacity(int maxCapacity) {
        this(maxCapacity, NO_CAPACITY);
    }

    private Capacity(int maxCapacity, int enrolled) {
        validateMaxCapacity(maxCapacity);

        this.maxCapacity = maxCapacity;
        this.enrolled = enrolled;
    }

    private void validateMaxCapacity(int maxCapacity) {
        if (maxCapacity < NO_CAPACITY) {
            throw new CapacityException("수용 인원은 음수일 수 없습니다.");
        }
    }

    public void enroll() {
        int afterEnrolled = this.enrolled + A_STUDENT;
        validateEnrolledNumber(afterEnrolled);

        this.enrolled = afterEnrolled;
    }

    private void validateEnrolledNumber(int afterEnrolled) {
        if (afterEnrolled > this.maxCapacity) {
            throw new CapacityException("수용 가능인원을 초과하여 신청하였습니다.");
        }
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getEnrolled() {
        return this.enrolled;
    }

    public boolean isAvailable() {
        return maxCapacity > this.enrolled;
    }
}