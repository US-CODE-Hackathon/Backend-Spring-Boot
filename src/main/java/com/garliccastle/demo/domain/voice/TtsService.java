package com.garliccastle.demo.domain.voice;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TtsService {

    private final RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<ByteArrayResource> fetchTtsAudio(String text) {
        String externalUrl = "http://34.64.140.206:8000/tts"; // 실제 TTS API 주소

        // 1. 요청 헤더 및 바디 구성
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(List.of(MediaType.APPLICATION_OCTET_STREAM, MediaType.ALL));

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("text", text);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        // 2. 요청
        ResponseEntity<byte[]> response = restTemplate.exchange(
                externalUrl,
                HttpMethod.POST,
                request,
                byte[].class
        );

        // 3. 에러 처리 및 변환
        byte[] mp3Bytes = response.getBody();
        if (response.getStatusCode() != HttpStatus.OK || mp3Bytes == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        ByteArrayResource resource = new ByteArrayResource(mp3Bytes);

        // 4. 응답 헤더 구성
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        responseHeaders.setContentLength(mp3Bytes.length);
        responseHeaders.setContentDisposition(ContentDisposition.inline().filename("tts.mp3").build());

        return new ResponseEntity<>(resource, responseHeaders, HttpStatus.OK);
    }
}