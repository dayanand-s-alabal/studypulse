package com.studypulse.controller;

import com.studypulse.dto.session.StudySessionResponse;
import com.studypulse.service.StudySessionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/sessions")
public class StudySessionController {

    private final StudySessionService studySessionService;

    public StudySessionController(
            StudySessionService studySessionService
    ) {
        this.studySessionService = studySessionService;
    }

    @PostMapping("/sprint/{sprintId}")
    public ResponseEntity<StudySessionResponse> createSession(
            @PathVariable Long sprintId,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date
    ) {

        StudySessionResponse response =
                studySessionService.createSession(
                        sprintId,
                        date
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudySessionResponse>
    getSessionById(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                studySessionService.getSessionById(id)
        );
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<StudySessionResponse>>
    getSessionsByUser(
            @PathVariable Long userId
    ) {

        return ResponseEntity.ok(
                studySessionService
                        .getSessionsByUser(userId)
        );
    }

    @GetMapping("/user/{userId}/date")
    public ResponseEntity<List<StudySessionResponse>>
    getSessionsByDate(
            @PathVariable Long userId,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date
    ) {

        return ResponseEntity.ok(
                studySessionService
                        .getSessionsByDate(
                                userId,
                                date
                        )
        );
    }

    @PatchMapping("/{id}/start")
    public ResponseEntity<StudySessionResponse>
    startSession(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                studySessionService.startSession(id)
        );
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<StudySessionResponse>
    completeSession(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                studySessionService.completeSession(id)
        );
    }

    @PatchMapping("/{id}/miss")
    public ResponseEntity<StudySessionResponse>
    markAsMissed(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                studySessionService.markAsMissed(id)
        );
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<StudySessionResponse>
    cancelSession(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                studySessionService.cancelSession(id)
        );
    }
}