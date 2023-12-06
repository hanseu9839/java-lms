package nextstep.courses.domain.session;

import java.util.Optional;

public interface SessionRepository {

    Optional<Session> findBySessionId(Long sessionId);
}
