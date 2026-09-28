package com.studypulse.repository;


import com.studypulse.entity.StudySprint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudySprintRepository
        extends JpaRepository<StudySprint, Long> {

    List<StudySprint> findByUserId(Long userId);

    List<StudySprint> findByUserIdAndDayOfWeek(
            Long userId,
            StudySprint.DayOfWeek dayOfWeek
    );

    List<StudySprint> findByUserIdAndIsActiveTrue(Long userId);

    List<StudySprint> findByUserIdAndDayOfWeekAndIsActiveTrue(
            Long userId,
            StudySprint.DayOfWeek dayOfWeek
    );
}
