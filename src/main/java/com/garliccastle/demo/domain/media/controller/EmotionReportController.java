package com.garliccastle.demo.domain.media.controller;

import com.garliccastle.demo.common.response.ApiResponse;
import com.garliccastle.demo.domain.media.dto.EmotionResponseDto;
import com.garliccastle.demo.domain.media.service.EmotionalReportService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class EmotionReportController {
    private final EmotionalReportService emotionalReportService;

    @GetMapping("/emotions")
    public ResponseEntity<ApiResponse<List<EmotionResponseDto>>> getAllEmotionalReports() {
        return ResponseEntity.ok(ApiResponse.success(emotionalReportService.getEmotionResponseAll()));
    }

    @GetMapping("/emotions/{id}")
    public ResponseEntity<ApiResponse<EmotionResponseDto>> getEmotionReportDetail(@PathVariable Long id) {

        EmotionResponseDto result = emotionalReportService.getEmotionResponseById(id);
        return ResponseEntity.ok(ApiResponse.success(result));
    }
}
