package com.garliccastle.demo.domain.voice;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class TtsService {
    private final RestTemplate restTemplate = new RestTemplate();

    public File requestTtsAndSaveToFile(String text) throws IOException {
        String url = "http://10.178.0.9:8000/tts"; // 실제 TTS API 주소로 변경

        // 1. 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // 2. 요청 본문 설정
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("text", text);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        // 3. 요청 보내고 파일(bytes)로 받기
        ResponseEntity<byte[]> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                request,
                byte[].class
        );

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("TTS 요청 실패: " + response.getStatusCode());
        }

        // 4. 임시 파일 저장
        byte[] audioBytes = response.getBody();
        File tempMp3 = Files.createTempFile("tts-result-", ".mp3").toFile();

        try (FileOutputStream fos = new FileOutputStream(tempMp3)) {
            fos.write(audioBytes);
        }

        return tempMp3; // 혹은 URL로 제공할 수도 있음
    }
}