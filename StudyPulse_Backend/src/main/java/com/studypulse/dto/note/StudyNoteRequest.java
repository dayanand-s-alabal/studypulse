package com.studypulse.dto.note;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudyNoteRequest {

    @NotBlank(message = "Key point cannot be empty")
    @Size(
            max = 2000,
            message = "Key point cannot exceed 2000 characters"
    )
    private String keyPoint;
}
