package com.studypulse.service;

import com.studypulse.dto.sprint.StudySprintRequest;
import com.studypulse.dto.sprint.StudySprintResponse;
import com.studypulse.entity.StudySprint;
import com.studypulse.entity.User;
import com.studypulse.mapper.StudySprintMapper;
import com.studypulse.repository.StudySprintRepository;
import com.studypulse.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudySprintService {

    private final StudySprintRepository studySprintRepository;
    private final UserRepository userRepository;

    public StudySprintService(
            StudySprintRepository studySprintRepository,
            UserRepository userRepository
    ) {
        this.studySprintRepository = studySprintRepository;
        this.userRepository = userRepository;
    }

    public StudySprintResponse createSprint(
            Long userId,
            StudySprintRequest request
    ) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found with id: " + userId
                        )
                );

        validateTime(
                request.getStartTime(),
                request.getEndTime()
        );

        StudySprint sprint = StudySprint.builder()
                .user(user)
                .subject(request.getSubject().trim())
                .dayOfWeek(request.getDayOfWeek())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .isActive(
                        request.getIsActive() == null
                                || request.getIsActive()
                )
                .build();

        StudySprint savedSprint =
                studySprintRepository.save(sprint);

        return StudySprintMapper.toResponse(savedSprint);
    }

    public List<StudySprintResponse> getAllSprints() {

        return studySprintRepository.findAll()
                .stream()
                .map(StudySprintMapper::toResponse)
                .toList();
    }

    public List<StudySprintResponse> getUserSprints(
            Long userId
    ) {

        return studySprintRepository
                .findByUserId(userId)
                .stream()
                .map(StudySprintMapper::toResponse)
                .toList();
    }

    public List<StudySprintResponse> getUserSprintsByDay(
            Long userId,
            StudySprint.DayOfWeek dayOfWeek
    ) {

        return studySprintRepository
                .findByUserIdAndDayOfWeekAndIsActiveTrue(
                        userId,
                        dayOfWeek
                )
                .stream()
                .map(StudySprintMapper::toResponse)
                .toList();
    }

    public StudySprint getSprintEntityById(Long id) {

        return studySprintRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Study sprint not found with id: " + id
                        )
                );
    }

    public StudySprintResponse getSprintById(Long id) {

        return StudySprintMapper.toResponse(
                getSprintEntityById(id)
        );
    }

    public StudySprintResponse updateSprint(
            Long id,
            StudySprintRequest request
    ) {

        StudySprint existingSprint =
                getSprintEntityById(id);

        validateTime(
                request.getStartTime(),
                request.getEndTime()
        );

        existingSprint.setSubject(
                request.getSubject().trim()
        );

        existingSprint.setDayOfWeek(
                request.getDayOfWeek()
        );

        existingSprint.setStartTime(
                request.getStartTime()
        );

        existingSprint.setEndTime(
                request.getEndTime()
        );

        if (request.getIsActive() != null) {
            existingSprint.setIsActive(
                    request.getIsActive()
            );
        }

        StudySprint updatedSprint =
                studySprintRepository.save(existingSprint);

        return StudySprintMapper.toResponse(updatedSprint);
    }

    public void deleteSprint(Long id) {

        StudySprint sprint = getSprintEntityById(id);

        studySprintRepository.delete(sprint);
    }

    public void deactivateSprint(Long id) {

        StudySprint sprint = getSprintEntityById(id);

        sprint.setIsActive(false);

        studySprintRepository.save(sprint);
    }

    private void validateTime(
            java.time.LocalTime startTime,
            java.time.LocalTime endTime
    ) {

        if (!endTime.isAfter(startTime)) {
            throw new IllegalArgumentException(
                    "End time must be after start time"
            );
        }
    }
}