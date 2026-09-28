package com.studypulse.controller;

import com.studypulse.dto.note.StudyNoteRequest;
import com.studypulse.dto.note.StudyNoteResponse;
import com.studypulse.service.StudyNoteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class StudyNoteController {

    private final StudyNoteService studyNoteService;

    public StudyNoteController(
            StudyNoteService studyNoteService
    ) {
        this.studyNoteService = studyNoteService;
    }

    @PostMapping("/session/{sessionId}")
    public ResponseEntity<StudyNoteResponse> addNote(
            @PathVariable Long sessionId,
            @Valid @RequestBody StudyNoteRequest request
    ) {

        StudyNoteResponse response =
                studyNoteService.addNote(
                        sessionId,
                        request.getKeyPoint()
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/session/{sessionId}")
    public ResponseEntity<List<StudyNoteResponse>>
    getNotesBySession(
            @PathVariable Long sessionId
    ) {

        return ResponseEntity.ok(
                studyNoteService
                        .getNotesBySession(sessionId)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudyNoteResponse>
    getNoteById(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                studyNoteService.getNoteById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudyNoteResponse>
    updateNote(
            @PathVariable Long id,
            @Valid @RequestBody StudyNoteRequest request
    ) {

        return ResponseEntity.ok(
                studyNoteService.updateNote(
                        id,
                        request.getKeyPoint()
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(
            @PathVariable Long id
    ) {

        studyNoteService.deleteNote(id);

        return ResponseEntity.noContent().build();
    }
}
