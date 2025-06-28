package com.garliccastle.demo.domain.responses.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class GCSRequest {
    private Long conversationId;
    private MultipartFile file;

}
