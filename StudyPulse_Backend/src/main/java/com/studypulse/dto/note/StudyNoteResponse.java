package com.studypulse.dto.note;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class StudyNoteResponse {

    private Long id;

    private Long sessionId;

    private String keyPoint;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
