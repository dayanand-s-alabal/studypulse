package com.studypulse.service;

import com.studypulse.dto.session.StudySessionResponse;
import com.studypulse.entity.StudySession;
import com.studypulse.entity.StudySprint;
import com.studypulse.mapper.StudySessionMapper;
import com.studypulse.repository.StudySessionRepository;
import com.studypulse.repository.StudySprintRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudySessionService {

    private final StudySessionRepository studySessionRepository;
    private final StudySprintRepository studySprintRepository;

    public StudySessionService(
            StudySessionRepository studySessionRepository,
            StudySprintRepository studySprintRepository
    ) {
        this.studySessionRepository = studySessionRepository;
        this.studySprintRepository = studySprintRepository;
    }

    public StudySessionResponse createSession(
            Long sprintId,
            LocalDate sessionDate
    ) {

        StudySprint sprint =
                studySprintRepository.findById(sprintId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Study sprint not found with id: "
                                                + sprintId
                                )
                        );

        if (!sprint.getIsActive()) {
            throw new RuntimeException(
                    "Cannot create session for inactive sprint"
            );
        }

        if (studySessionRepository
                .findBySprintIdAndSessionDate(
                        sprintId,
                        sessionDate
                )
                .isPresent()) {

            throw new RuntimeException(
                    "Session already exists for this date"
            );
        }

        StudySession session =
                StudySession.builder()
                        .sprint(sprint)
                        .sessionDate(sessionDate)
                        .status(
                                StudySession.SessionStatus.UPCOMING
                        )
                        .build();

        StudySession savedSession =
                studySessionRepository.save(session);

        return StudySessionMapper.toResponse(
                savedSession
        );
    }

    public StudySessionResponse getSessionById(Long id) {

        return StudySessionMapper.toResponse(
                getSessionEntityById(id)
        );
    }

    public StudySession getSessionEntityById(Long id) {

        return studySessionRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Study session not found with id: "
                                        + id
                        )
                );
    }

    public List<StudySessionResponse> getSessionsByUser(
            Long userId
    ) {

        return studySessionRepository
                .findBySprintUserId(userId)
                .stream()
                .map(StudySessionMapper::toResponse)
                .toList();
    }

    public List<StudySessionResponse> getSessionsByDate(
            Long userId,
            LocalDate date
    ) {

        return studySessionRepository
                .findBySprintUserIdAndSessionDate(
                        userId,
                        date
                )
                .stream()
                .map(StudySessionMapper::toResponse)
                .toList();
    }

    public StudySessionResponse startSession(Long id) {

        StudySession session =
                getSessionEntityById(id);

        session.setStatus(
                StudySession.SessionStatus.IN_PROGRESS
        );

        session.setActualStartTime(
                LocalDateTime.now()
        );

        return StudySessionMapper.toResponse(
                studySessionRepository.save(session)
        );
    }

    public StudySessionResponse completeSession(Long id) {

        StudySession session =
                getSessionEntityById(id);

        session.setStatus(
                StudySession.SessionStatus.COMPLETED
        );

        session.setActualEndTime(
                LocalDateTime.now()
        );

        return StudySessionMapper.toResponse(
                studySessionRepository.save(session)
        );
    }

    public StudySessionResponse markAsMissed(Long id) {

        StudySession session =
                getSessionEntityById(id);

        session.setStatus(
                StudySession.SessionStatus.MISSED
        );

        return StudySessionMapper.toResponse(
                studySessionRepository.save(session)
        );
    }

    public StudySessionResponse cancelSession(Long id) {

        StudySession session =
                getSessionEntityById(id);

        session.setStatus(
                StudySession.SessionStatus.CANCELLED
        );

        return StudySessionMapper.toResponse(
                studySessionRepository.save(session)
        );
    }
}