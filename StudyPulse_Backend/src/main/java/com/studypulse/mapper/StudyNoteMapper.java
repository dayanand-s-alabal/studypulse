package com.studypulse.mapper;

import com.studypulse.dto.note.StudyNoteResponse;
import com.studypulse.entity.StudyNote;

public class StudyNoteMapper {

    private StudyNoteMapper() {
    }

    public static StudyNoteResponse toResponse(
            StudyNote note
    ) {

        return StudyNoteResponse.builder()
                .id(note.getId())
                .sessionId(note.getSession().getId())
                .keyPoint(note.getKeyPoint())
                .createdAt(note.getCreatedAt())
                .updatedAt(note.getUpdatedAt())
                .build();
    }
}