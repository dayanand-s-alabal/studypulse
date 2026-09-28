package com.studypulse.repository;


import com.studypulse.entity.StudyNote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudyNoteRepository
        extends JpaRepository<StudyNote, Long> {

    List<StudyNote> findBySessionId(Long sessionId);
}