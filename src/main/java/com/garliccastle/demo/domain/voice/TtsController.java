package com.garliccastle.demo.domain.voice;

import com.garliccastle.demo.domain.voice.dto.TtsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TtsController {
    private final TtsService ttsService;
    /**
     * 텍스트를 받아서 TTS 변환 후 mp3 파일로 반환
     */
    @PostMapping("/tts")
    public ResponseEntity<ByteArrayResource> convertTextToSpeech(TtsDto ttsDto) {
        return ttsService.fetchTtsAudio(ttsDto.getText());
    }
}