package com.studypulse.service;


import com.studypulse.dto.note.StudyNoteResponse;
import com.studypulse.entity.StudyNote;
import com.studypulse.entity.StudySession;
import com.studypulse.mapper.StudyNoteMapper;
import com.studypulse.repository.StudyNoteRepository;
import com.studypulse.repository.StudySessionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudyNoteService {

    private final StudyNoteRepository studyNoteRepository;
    private final StudySessionRepository studySessionRepository;

    public StudyNoteService(
            StudyNoteRepository studyNoteRepository,
            StudySessionRepository studySessionRepository
    ) {
        this.studyNoteRepository = studyNoteRepository;
        this.studySessionRepository = studySessionRepository;
    }

    public StudyNoteResponse addNote(
            Long sessionId,
            String keyPoint
    ) {

        StudySession session =
                studySessionRepository.findById(sessionId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Study session not found with id: "
                                                + sessionId
                                )
                        );

        StudyNote note =
                StudyNote.builder()
                        .session(session)
                        .keyPoint(keyPoint.trim())
                        .build();

        return StudyNoteMapper.toResponse(
                studyNoteRepository.save(note)
        );
    }

    public List<StudyNoteResponse> getNotesBySession(
            Long sessionId
    ) {

        return studyNoteRepository
                .findBySessionId(sessionId)
                .stream()
                .map(StudyNoteMapper::toResponse)
                .toList();
    }

    public StudyNoteResponse getNoteById(Long id) {

        return StudyNoteMapper.toResponse(
                getNoteEntityById(id)
        );
    }

    public StudyNote getNoteEntityById(Long id) {

        return studyNoteRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Study note not found with id: " + id
                        )
                );
    }

    public StudyNoteResponse updateNote(
            Long id,
            String keyPoint
    ) {

        StudyNote note = getNoteEntityById(id);

        note.setKeyPoint(keyPoint.trim());

        return StudyNoteMapper.toResponse(
                studyNoteRepository.save(note)
        );
    }

    public void deleteNote(Long id) {

        StudyNote note = getNoteEntityById(id);

        studyNoteRepository.delete(note);
    }
}