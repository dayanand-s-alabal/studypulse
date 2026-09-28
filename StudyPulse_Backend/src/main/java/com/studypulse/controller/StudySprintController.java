package com.studypulse.controller;

import com.studypulse.dto.sprint.StudySprintRequest;
import com.studypulse.dto.sprint.StudySprintResponse;
import com.studypulse.entity.StudySprint;
import com.studypulse.service.StudySprintService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sprints")
public class StudySprintController {

    private final StudySprintService studySprintService;

    public StudySprintController(
            StudySprintService studySprintService
    ) {
        this.studySprintService = studySprintService;
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<StudySprintResponse> createSprint(
            @PathVariable Long userId,
            @Valid @RequestBody StudySprintRequest request
    ) {

        StudySprintResponse response =
                studySprintService.createSprint(
                        userId,
                        request
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<StudySprintResponse>>
    getAllSprints() {

        return ResponseEntity.ok(
                studySprintService.getAllSprints()
        );
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<StudySprintResponse>>
    getUserSprints(
            @PathVariable Long userId
    ) {

        return ResponseEntity.ok(
                studySprintService.getUserSprints(userId)
        );
    }

    @GetMapping("/user/{userId}/day/{day}")
    public ResponseEntity<List<StudySprintResponse>>
    getSprintsByDay(
            @PathVariable Long userId,
            @PathVariable StudySprint.DayOfWeek day
    ) {

        return ResponseEntity.ok(
                studySprintService
                        .getUserSprintsByDay(
                                userId,
                                day
                        )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudySprintResponse>
    getSprintById(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                studySprintService.getSprintById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudySprintResponse>
    updateSprint(
            @PathVariable Long id,
            @Valid @RequestBody StudySprintRequest request
    ) {

        return ResponseEntity.ok(
                studySprintService.updateSprint(
                        id,
                        request
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSprint(
            @PathVariable Long id
    ) {

        studySprintService.deleteSprint(id);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateSprint(
            @PathVariable Long id
    ) {

        studySprintService.deactivateSprint(id);

        return ResponseEntity.noContent().build();
    }
}
