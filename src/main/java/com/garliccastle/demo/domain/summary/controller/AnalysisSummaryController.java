package com.garliccastle.demo.domain.summary.controller;

import com.garliccastle.demo.common.response.ApiResponse;
import com.garliccastle.demo.domain.summary.dto.AnalysisSummaryReport;
import com.garliccastle.demo.domain.summary.service.AnalysisSummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AnalysisSummaryController {
    private final AnalysisSummaryService analysisSummaryService;

    @GetMapping("/reports/weekly")
    public ResponseEntity<ApiResponse<AnalysisSummaryReport>> getAnalysisSummaryReportWeekly(){
        AnalysisSummaryReport analysisSummaryReport = analysisSummaryService.getAnalysisSummaryReport();
        return ResponseEntity.ok(ApiResponse.success(analysisSummaryReport));
    }
}
