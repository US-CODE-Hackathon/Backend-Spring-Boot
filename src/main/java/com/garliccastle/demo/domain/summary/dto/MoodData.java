package com.garliccastle.demo.domain.summary.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MoodData {
    private String day;       // 요일 (예: "월", "화" ...)
    private int positive;
}

