package com.garliccastle.demo.domain.responses.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionAnswerPairDto {
    private String question;
    private String answer;
}
