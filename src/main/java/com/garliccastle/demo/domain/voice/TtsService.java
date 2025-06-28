package com.garliccastle.demo.domain.voice;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TtsService {
        private final RestTemplate restTemplate;
        //String externalUrl = "http://34.64.140.206:8000/tts"; // 외부 TTS API 주소

        public ResponseEntity<ByteArrayResource> fetchTtsAudio(String text) {
                String externalUrl = "http://10.178.0.9:8000/tts";

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

                MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
                body.add("text", text);

                HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

                ResponseEntity<byte[]> response = restTemplate.exchange(
                        externalUrl,
                        HttpMethod.POST,
                        request,
                        byte[].class
                );

                if (response.getStatusCode() != HttpStatus.OK || response.getBody() == null) {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                }

                byte[] mp3Bytes = response.getBody();
                ByteArrayResource resource = new ByteArrayResource(mp3Bytes);

                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                responseHeaders.setContentLength(mp3Bytes.length);
                responseHeaders.setContentDisposition(
                        ContentDisposition.inline().filename("tts.mp3").build()
                );

                return new ResponseEntity<>(resource, responseHeaders, HttpStatus.OK);
        }
}