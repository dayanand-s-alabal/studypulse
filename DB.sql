CREATE DATABASE IF NOT EXISTS study_sprint_db
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE study_sprint_db;

-- ==========================================
-- USERS
-- ==========================================

CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    name VARCHAR(100) NOT NULL,

    email VARCHAR(255) NOT NULL UNIQUE,

    password VARCHAR(255) NOT NULL,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP
);


-- ==========================================
-- STUDY SPRINTS
-- Recurring weekly timetable
-- ==========================================

CREATE TABLE study_sprints (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    user_id BIGINT NOT NULL,

    subject VARCHAR(100) NOT NULL,

    day_of_week ENUM(
        'MONDAY',
        'TUESDAY',
        'WEDNESDAY',
        'THURSDAY',
        'FRIDAY',
        'SATURDAY',
        'SUNDAY'
    ) NOT NULL,

    start_time TIME NOT NULL,

    end_time TIME NOT NULL,

    is_active BOOLEAN NOT NULL DEFAULT TRUE,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_sprint_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    CONSTRAINT chk_sprint_time
        CHECK (end_time > start_time)
);


-- ==========================================
-- STUDY SESSIONS
-- Actual occurrence of a sprint
-- ==========================================

CREATE TABLE study_sessions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    sprint_id BIGINT NOT NULL,

    session_date DATE NOT NULL,

    status ENUM(
        'UPCOMING',
        'IN_PROGRESS',
        'COMPLETED',
        'MISSED',
        'CANCELLED'
    ) NOT NULL DEFAULT 'UPCOMING',

    actual_start_time DATETIME NULL,

    actual_end_time DATETIME NULL,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_session_sprint
        FOREIGN KEY (sprint_id)
        REFERENCES study_sprints(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    CONSTRAINT uk_sprint_session_date
        UNIQUE (sprint_id, session_date)
);


-- ==========================================
-- STUDY NOTES
-- Key points recorded during a session
-- ==========================================

CREATE TABLE study_notes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    session_id BIGINT NOT NULL,

    key_point TEXT NOT NULL,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_note_session
        FOREIGN KEY (session_id)
        REFERENCES study_sessions(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);


-- ==========================================
-- INDEXES
-- ==========================================

CREATE INDEX idx_sprints_user
ON study_sprints(user_id);

CREATE INDEX idx_sprints_user_day
ON study_sprints(user_id, day_of_week);

CREATE INDEX idx_sessions_date
ON study_sessions(session_date);

CREATE INDEX idx_sessions_sprint
ON study_sessions(sprint_id);

CREATE INDEX idx_notes_session
ON study_notes(session_id);