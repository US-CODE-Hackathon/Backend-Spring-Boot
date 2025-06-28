package com.garliccastle.demo.domain.prompts.controller;

import com.garliccastle.demo.common.response.ApiResponse;
import com.garliccastle.demo.domain.prompts.dto.PromptResponseDto;
import com.garliccastle.demo.domain.prompts.service.PromptsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequiredArgsConstructor
public class PromptController {
    private final PromptsService promptsService;

    @GetMapping("/question/emotion/{conversationId}")
    public ResponseEntity<ApiResponse<PromptResponseDto>> getRandomEmotionPrompt(@PathVariable Long conversationId) {
        PromptResponseDto dto = promptsService.emotionPrompt(conversationId);
        return ResponseEntity.ok(ApiResponse.success(dto));
    }

    @GetMapping("/question/biography/{conversationId}")
    public ResponseEntity<ApiResponse<PromptResponseDto>> getAuthBiographyPrompt(@PathVariable Long conversationId) {
        PromptResponseDto dto = promptsService.authBiographyPrompt(conversationId);
        return ResponseEntity.ok(ApiResponse.success(dto));
    }
}
