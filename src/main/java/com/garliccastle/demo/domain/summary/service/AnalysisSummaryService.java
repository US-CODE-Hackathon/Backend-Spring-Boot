package com.garliccastle.demo.domain.summary.service;
import com.garliccastle.demo.domain.media.entity.EmotionalReport;
import com.garliccastle.demo.domain.media.repository.EmotionalReportRepository;
import com.garliccastle.demo.domain.summary.dto.AiAnalysisResponse;
import com.garliccastle.demo.domain.summary.dto.AnalysisSummaryReport;
import com.garliccastle.demo.domain.summary.dto.MoodData;
import com.garliccastle.demo.domain.summary.entity.WeekAnalysisSummary;
import com.garliccastle.demo.domain.summary.repository.AnalysisSummaryRepository;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class AnalysisSummaryService {
    private final AnalysisSummaryRepository summaryRepository;
    private final EmotionalReportRepository emotionalReportRepository;
    private final RestTemplate restTemplate;

    private static final String AI_ANALYSIS_URL = "http://10.178.0.9:8000/summary/analyze/7";

    public AnalysisSummaryReport getAnalysisSummaryReport(){
        AiAnalysisResponse aiWeekSummaryAnalyze = getAiWeekSummaryAnalyze();

        List<MoodData> moodData = convertToChartData();
        return new AnalysisSummaryReport(moodData,aiWeekSummaryAnalyze);
    }

    private AiAnalysisResponse getAiWeekSummaryAnalyze() {
        WeekAnalysisSummary weekAnalysisSummary = summaryRepository.findAll().getFirst();
        return new AiAnalysisResponse(weekAnalysisSummary.getPositiveRatio(), weekAnalysisSummary.getPatternSummary());
    }


    private List<MoodData> convertToChartData() {
        List<EmotionalReport> last7DaysReports = emotionalReportRepository.findTop7ByOrderByCreatedAtDesc();


        Map<DayOfWeek, List<Integer>> dayScoreMap = new HashMap<>();

        for (EmotionalReport report : last7DaysReports) {
            int score = switch (report.getSentiment()) {
                case "긍정" -> 80;
                case "보통" -> 60;
                case "부정" -> 40;
                default -> 0;
            };

            DayOfWeek dayOfWeek = report.getCreatedAt().getDayOfWeek();
            dayScoreMap.computeIfAbsent(dayOfWeek, k -> new ArrayList<>()).add(score);
        }

        // 요일 순서대로 정렬 및 평균 계산
        return Arrays.stream(DayOfWeek.values())
                .map(day -> {
                    List<Integer> scores = dayScoreMap.getOrDefault(day, List.of());
                    int avg = scores.isEmpty() ? 0 :
                            (int) scores.stream().mapToInt(Integer::intValue).average().orElse(0);

                    String koreanDay = switch (day) {
                        case MONDAY -> "월";
                        case TUESDAY -> "화";
                        case WEDNESDAY -> "수";
                        case THURSDAY -> "목";
                        case FRIDAY -> "금";
                        case SATURDAY -> "토";
                        case SUNDAY -> "일";
                    };

                    return new MoodData(koreanDay, avg);
                })
                .toList();
    }

    public void updateAiWeekSummaryAnalyze(){
        try {
            ResponseEntity<AiAnalysisResponse> response = restTemplate.getForEntity(
                    AI_ANALYSIS_URL,
                    AiAnalysisResponse.class
            );

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                AiAnalysisResponse result = response.getBody();
                Long ratio = result.getPositiveRatio();
                String summary = result.getPatternSummary();
                WeekAnalysisSummary weekAnalysisSummary = summaryRepository.findAll().getFirst();
                weekAnalysisSummary.updateAi(ratio, summary);
                summaryRepository.save(weekAnalysisSummary);
            } else {
                System.err.println("AI 분석 요청 실패: " + response.getStatusCode());
            }
        } catch (Exception e) {
            System.err.println("AI 분석 요청 중 오류 발생: " + e.getMessage());
        }
    }
}
