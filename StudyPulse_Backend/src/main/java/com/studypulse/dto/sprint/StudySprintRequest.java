package com.studypulse.dto.sprint;

import com.studypulse.entity.StudySprint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class StudySprintRequest {

    @NotBlank(message = "Subject is required")
    @Size(
            min = 1,
            max = 100,
            message = "Subject must be between 1 and 100 characters"
    )
    private String subject;

    @NotNull(message = "Day of week is required")
    private StudySprint.DayOfWeek dayOfWeek;

    @NotNull(message = "Start time is required")
    private LocalTime startTime;

    @NotNull(message = "End time is required")
    private LocalTime endTime;

    private Boolean isActive = true;
}
