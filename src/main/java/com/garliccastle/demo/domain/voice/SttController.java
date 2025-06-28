package com.garliccastle.demo.domain.voice;

import com.garliccastle.demo.common.response.ApiResponse;
import java.io.File;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class SttController {

    private final SttService sttService;

    @PostMapping("/stt")
    public ResponseEntity<ApiResponse<String>> handleAudioUpload(@RequestParam("file") MultipartFile multipartFile)
            throws IOException {
        // IOException or IllegalStateException 발생 시 그대로 전파됨

        File tempFile = File.createTempFile("temp-audio", ".wav");
        multipartFile.transferTo(tempFile);

        String result = sttService.sendAudioForTranscription(tempFile);

        // 삭제는 finally 로 옮기면 안정성 ↑
        tempFile.delete();

        return ResponseEntity.ok(ApiResponse.success(result));
    }
}