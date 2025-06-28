package com.garliccastle.demo.domain.voice;

import com.garliccastle.demo.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/voice")
public class TtsController {
    private final TtsService ttsService;

    /**
     * 텍스트를 받아서 TTS 변환 후 mp3 파일로 반환
     */
    @PostMapping("/tts")
    public ResponseEntity<Resource> convertTextToSpeech(@RequestParam("text") String text) throws IOException {
        // TTS API 호출 → MP3 파일 생성
        File mp3File = ttsService.requestTtsAndSaveToFile(text);
        FileSystemResource resource = new FileSystemResource(mp3File);

        // HTTP 응답 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDisposition(ContentDisposition.builder("attachment")
                .filename("tts-result.mp3")
                .build());

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }
}