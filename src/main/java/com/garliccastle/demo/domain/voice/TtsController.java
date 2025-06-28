package com.garliccastle.demo.domain.voice;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TtsController {
    private final TtsService ttsService;
    /**
     * 텍스트를 받아서 TTS 변환 후 mp3 파일로 반환
     */
    @PostMapping("/tts")
    public ResponseEntity<ByteArrayResource> convertTextToSpeech(@RequestParam("text") String text) {
        return ttsService.fetchTtsAudio(text);
    }
}