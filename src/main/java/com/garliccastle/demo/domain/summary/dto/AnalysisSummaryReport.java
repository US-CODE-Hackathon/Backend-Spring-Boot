package com.garliccastle.demo.domain.summary.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnalysisSummaryReport {
    private List<MoodData> moodData;
    private AiAnalysisResponse aiAnalysisResponse;

}
