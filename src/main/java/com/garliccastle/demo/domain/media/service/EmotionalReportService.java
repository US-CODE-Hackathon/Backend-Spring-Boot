package com.garliccastle.demo.domain.media.service;

import com.garliccastle.demo.domain.media.dto.EmotionResponseDto;
import com.garliccastle.demo.domain.media.entity.EmotionalReport;
import com.garliccastle.demo.domain.media.repository.EmotionalReportRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmotionalReportService {
    private final EmotionalReportRepository emotionalReportRepository;

    public List<EmotionResponseDto> getEmotionResponseAll() {
        List<EmotionalReport> all = emotionalReportRepository.findAll();

        return all.stream()
                .map(EmotionResponseDto::from) // 정적 팩토리 메서드 사용 (추천)
                .collect(Collectors.toList());
    }

    public EmotionResponseDto getEmotionResponseById(Long id) {
        EmotionalReport report = emotionalReportRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("ID 오류"));  // 예외 처리 필요

        report.updateIsFirst(false);
        emotionalReportRepository.save(report);
        return EmotionResponseDto.from(report);
    }
}
