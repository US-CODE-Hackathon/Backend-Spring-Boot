package com.garliccastle.demo.domain.media.dto;

import com.garliccastle.demo.domain.media.entity.EmotionalReport;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmotionResponseDto {
    private Long emotionalReportId;
    private String imageUrl;
    private String emotion;
    private String title;
    private String aiSummary;
    private boolean isFirst;
    private String date;
    private String time;

    public static EmotionResponseDto from(EmotionalReport report) {
        return new EmotionResponseDto(
                report.getEmotionalReportId(),
                report.getImageUrl(),
                report.getSentiment(),
                report.getTitle(),
                report.getSummary(),
                report.isFirst(),
                report.getCreatedAt().toLocalDate().toString() ,// yyyy-MM-dd 형식
                report.getTime()
        );
    }
}
