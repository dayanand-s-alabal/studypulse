package com.studypulse.mapper;


import com.studypulse.dto.session.StudySessionResponse;
import com.studypulse.entity.StudySession;

public class StudySessionMapper {

    private StudySessionMapper() {
    }

    public static StudySessionResponse toResponse(
            StudySession session
    ) {

        return StudySessionResponse.builder()
                .id(session.getId())
                .sprintId(session.getSprint().getId())
                .subject(session.getSprint().getSubject())
                .sessionDate(session.getSessionDate())
                .status(session.getStatus())
                .actualStartTime(session.getActualStartTime())
                .actualEndTime(session.getActualEndTime())
                .createdAt(session.getCreatedAt())
                .updatedAt(session.getUpdatedAt())
                .build();
    }
}
