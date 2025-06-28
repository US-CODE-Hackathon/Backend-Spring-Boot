package com.garliccastle.demo.domain.summary.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AiAnalysisResponse {
    @JsonProperty("positive_ratio")
    private Long positiveRatio;

    @JsonProperty("pattern_summary")
    private String patternSummary;
}
