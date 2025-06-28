package com.garliccastle.demo.domain.media.repository;

import com.garliccastle.demo.domain.media.entity.EmotionalReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmotionalReportRepository extends JpaRepository<EmotionalReport, Long> {
}
