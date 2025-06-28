package com.garliccastle.demo.domain.responses.controller;

import com.garliccastle.demo.common.response.ApiResponse;
import com.garliccastle.demo.domain.responses.dto.GCSRequest;
import com.garliccastle.demo.domain.responses.dto.ResponseSaveRequest;
import com.garliccastle.demo.domain.responses.service.GcsService;
import com.garliccastle.demo.domain.responses.service.ResponsesService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ResponsesController {
    private final ResponsesService responsesService;
    private final GcsService gcsService;

    @PostMapping("/question")
    public ResponseEntity<ApiResponse<Void>> saveEmotionResponse(
            @RequestBody ResponseSaveRequest request
    ) {
        responsesService.saveResponse(request);
        return ResponseEntity.ok(ApiResponse.success());
    }

    @PostMapping("/api/gcs/upload")
    public ResponseEntity<ApiResponse<Void>> objectUpload(GCSRequest gcsRequest) throws IOException {

        String url = gcsService.uploadObject(gcsRequest);
        System.out.println(url);
        responsesService.analyzeResponses(gcsRequest.getConversationId());

        return ResponseEntity.ok(ApiResponse.success());
    }


}
