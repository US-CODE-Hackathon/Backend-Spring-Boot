package com.garliccastle.demo.domain.responses.service;

import com.garliccastle.demo.domain.conversations.entity.Conversations;
import com.garliccastle.demo.domain.conversations.repository.ConversationsRepository;
import com.garliccastle.demo.domain.prompts.entity.Prompts;
import com.garliccastle.demo.domain.prompts.repository.PromptsRepository;
import com.garliccastle.demo.domain.responses.dto.EmotionAnalysisResponseDto;
import com.garliccastle.demo.domain.responses.dto.QuestionAnswerPairDto;
import com.garliccastle.demo.domain.responses.dto.ResponseSaveRequest;
import com.garliccastle.demo.domain.responses.entity.Responses;
import com.garliccastle.demo.domain.responses.repository.ResponsesRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ResponsesService {
    private final ResponsesRepository responsesRepository;
    private final PromptsRepository promptsRepository;
    private final ConversationsRepository conversationsRepository;
    private final RestTemplate restTemplate;

    private static final String AI_ANALYSIS_URL = "http://10.178.0.3:8000/summary";

    public void saveResponse(ResponseSaveRequest request) {
        Conversations conversation = conversationsRepository.findById(request.getConversationId())
                .orElseThrow(() -> new IllegalArgumentException("해당 대화 세션이 존재하지 않습니다."));

        Prompts prompt = promptsRepository.findById(request.getQuestionId())
                .orElseThrow(() -> new IllegalArgumentException("해당 질문이 존재하지 않습니다."));

        Responses response = Responses.builder()
                .conversation(conversation)
                .prompt(prompt)
                .content(request.getContent())
                .createdAt(LocalDateTime.now())
                .build();

        responsesRepository.save(response);
    }

    public EmotionAnalysisResponseDto analyzeResponses(Long conversationId) {
        List<Responses> responses = responsesRepository.findAllByConversation_ConversationId(conversationId);

        List<QuestionAnswerPairDto> payload = responses.stream()
                .map(response -> QuestionAnswerPairDto.builder()
                        .question(response.getPrompt().getContent())
                        .answer(response.getContent())
                        .build())
                .toList();

        // HTTP Header 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // HttpEntity 생성
        HttpEntity<List<QuestionAnswerPairDto>> requestEntity = new HttpEntity<>(payload, headers);

        // 전송 및 응답 처리
        try {
            ResponseEntity<EmotionAnalysisResponseDto> response = restTemplate.exchange(
                    AI_ANALYSIS_URL,
                    HttpMethod.POST,
                    requestEntity,
                    EmotionAnalysisResponseDto.class
            );
            return response.getBody();
        } catch (RestClientException e) {
            System.err.println("AI 분석 서버 호출 실패: " + e.getMessage());
        }
        return null;
    }

}
