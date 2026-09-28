package com.studypulse.dto.sprint;

import com.studypulse.entity.StudySprint;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Builder
public class StudySprintResponse {

    private Long id;

    private Long userId;

    private String subject;

    private StudySprint.DayOfWeek dayOfWeek;

    private LocalTime startTime;

    private LocalTime endTime;

    private Boolean isActive;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
