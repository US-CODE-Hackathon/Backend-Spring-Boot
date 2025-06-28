package com.garliccastle.demo.domain.prompts.service;

import com.garliccastle.demo.domain.prompts.dto.PromptResponseDto;
import com.garliccastle.demo.domain.prompts.entity.Prompts;
import com.garliccastle.demo.domain.prompts.repository.PromptsRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PromptsService {
    private final PromptsRepository promptsRepository;

    public PromptResponseDto emotionPrompt(Long conversationId){
        Prompts prompt = promptsRepository.findRandomEmotionPrompt()
                .orElseThrow(() -> new IllegalStateException("EMOTION 질문이 없습니다."));

        return PromptResponseDto.builder()
                .conversationId(conversationId)
                .questionId(prompt.getPromptId())
                .type(prompt.getType())
                .question(prompt.getContent())
                .build();
    }

    public PromptResponseDto authBiographyPrompt(Long conversationId){
        Prompts prompt = promptsRepository.findRandomAUTOBIOGRAPHYPrompt()
                .orElseThrow(() -> new IllegalStateException("EMOTION 질문이 없습니다."));

        return PromptResponseDto.builder()
                .conversationId(conversationId)
                .questionId(prompt.getPromptId())
                .type(prompt.getType())
                .question(prompt.getContent())
                .build();
    }
}
