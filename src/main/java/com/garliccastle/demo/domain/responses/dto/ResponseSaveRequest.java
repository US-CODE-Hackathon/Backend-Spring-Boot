package com.garliccastle.demo.domain.responses.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseSaveRequest {
    private Long conversationId;
    private String type;
    private Long questionId;
    private String question;
    private String content;
}
