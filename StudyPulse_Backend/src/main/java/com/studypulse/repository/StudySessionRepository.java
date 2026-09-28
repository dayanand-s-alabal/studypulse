package com.studypulse.repository;

import com.studypulse.entity.StudySession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface StudySessionRepository
        extends JpaRepository<StudySession, Long> {

    List<StudySession> findBySessionDate(LocalDate sessionDate);

    List<StudySession> findBySprintUserId(Long userId);

    List<StudySession> findBySprintUserIdAndSessionDate(
            Long userId,
            LocalDate sessionDate
    );

    List<StudySession> findBySprintUserIdAndStatus(
            Long userId,
            StudySession.SessionStatus status
    );

    Optional<StudySession> findBySprintIdAndSessionDate(
            Long sprintId,
            LocalDate sessionDate
    );
}
