package com.garliccastle.demo.domain.responses.service;

import com.garliccastle.demo.domain.responses.dto.GCSRequest;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

@Service
@RequiredArgsConstructor
public class GcsService {
    @Value("${spring.cloud.gcp.storage.bucket}")
    private String bucketName;

    public String uploadObject(GCSRequest gcsRequest) throws IOException {
        String keyFileName = "liquid-braid-463809-s5-5b51278c8e35.json";
        InputStream keyFile = ResourceUtils.getURL("classpath:" + keyFileName).openStream();

        Storage storage = StorageOptions.newBuilder()
                .setCredentials(GoogleCredentials.fromStream(keyFile))
                .build()
                .getService();

        String fileName = gcsRequest.getFile().getOriginalFilename();

        BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, fileName)
                .setContentType(gcsRequest.getFile().getContentType()).build();

        storage.create(blobInfo, gcsRequest.getFile().getInputStream());

        URL signedUrl = storage.signUrl(
                blobInfo,
                7, TimeUnit.DAYS, // 최대 7일
                Storage.SignUrlOption.withV4Signature() // V4 방식
        );

        return signedUrl.toString();
    }
}
