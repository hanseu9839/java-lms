package nextstep.courses.domain.course.session;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Applicants implements Iterable<NsUser> {
    private final List<NsUser> applicants;

    public Applicants() {
        this(new ArrayList<>());
    }

    public Applicants(List<NsUser> applicants) {
        this.applicants = applicants;
    }

    public NsUser find(int index) {
        return this.applicants.get(index);
    }

    public int size() {
        return this.applicants.size();
    }

    public void addApplicant(NsUser applicant, SessionState sessionState) {
        checkApplicantAlreadyExisted(applicant);
        checkChargedAndApplySizeIsValid(sessionState);
        this.applicants.add(applicant);
    }

    private void checkApplicantAlreadyExisted(NsUser applicant) {
        if (this.applicants.contains(applicant)) {
            throw new IllegalArgumentException("이미 강의를 신청하였습니다.");
        }
    }

    private void checkChargedAndApplySizeIsValid(SessionState sessionState) {
        if (sessionState.chargedAndFull(applicants)) {
            throw new IllegalArgumentException("수강 인원은 정원을 초과할 수 없습니다.");
        }
    }

    public void checkApprovePossible(NsUser applicant, SessionState sessionState) {
        checkApplicantExists(applicant);
        checkChargedAndApplySizeIsValid(sessionState);
    }

    private void checkApplicantExists(NsUser applicant) {
        if (!this.applicants.contains(applicant)) {
            throw new IllegalArgumentException("수강 신청 이력이 없습니다.");
        }
    }

    public void checkCancelPossible(NsUser applicant, SessionState sessionState) {
        checkApplicantExists(applicant);
    }

    @Override
    public Iterator<NsUser> iterator() {
        return this.applicants.iterator();
    }

    @Override
    public String toString() {
        return "Applicants{" +
                "applicants=" + applicants +
                '}';
    }
}