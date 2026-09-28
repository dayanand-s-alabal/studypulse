package com.studypulse.mapper;
import com.studypulse.dto.sprint.StudySprintResponse;
import com.studypulse.entity.StudySprint;

public class StudySprintMapper {

    private StudySprintMapper() {
    }

    public static StudySprintResponse toResponse(
            StudySprint sprint
    ) {

        return StudySprintResponse.builder()
                .id(sprint.getId())
                .userId(sprint.getUser().getId())
                .subject(sprint.getSubject())
                .dayOfWeek(sprint.getDayOfWeek())
                .startTime(sprint.getStartTime())
                .endTime(sprint.getEndTime())
                .isActive(sprint.getIsActive())
                .createdAt(sprint.getCreatedAt())
                .updatedAt(sprint.getUpdatedAt())
                .build();
    }
}
