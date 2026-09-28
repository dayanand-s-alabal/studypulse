package com.studypulse.dto.session;


import com.studypulse.entity.StudySession;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class StudySessionResponse {

    private Long id;

    private Long sprintId;

    private String subject;

    private LocalDate sessionDate;

    private StudySession.SessionStatus status;

    private LocalDateTime actualStartTime;

    private LocalDateTime actualEndTime;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
