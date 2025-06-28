package com.garliccastle.demo.domain.media.repository;

import com.garliccastle.demo.domain.media.entity.EmotionalReport;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmotionalReportRepository extends JpaRepository<EmotionalReport, Long> {
    List<EmotionalReport> findTop7ByOrderByCreatedAtDesc();
}
