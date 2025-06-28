package com.garliccastle.demo.domain.prompts.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PromptResponseDto {
    private Long conversationId;
    private String type;  // 예: EMOTION, AUTOBIOGRAPHY 등 enum
    private Long questionId;
    private String question;  // 질문 내용
}
