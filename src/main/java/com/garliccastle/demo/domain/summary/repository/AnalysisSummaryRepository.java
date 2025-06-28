package com.garliccastle.demo.domain.summary.repository;

import com.garliccastle.demo.domain.summary.entity.WeekAnalysisSummary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnalysisSummaryRepository extends JpaRepository<WeekAnalysisSummary, Long> {

}
