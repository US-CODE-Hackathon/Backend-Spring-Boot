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
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GcsService {

    private static final String BUCKET_NAME = "usus-bucket";
    private final ResourceLoader resourceLoader;

    public String uploadObject(GCSRequest gcsRequest) throws IOException {
        // ✅ 외부 키 파일 위치 설정 (application.yml에서 불러오도록 개선 가능)
        Resource resource = resourceLoader.getResource("file:/app/secrets/gcpkey.json");
        InputStream keyFile = resource.getInputStream();

        Storage storage = StorageOptions.newBuilder()
                .setCredentials(GoogleCredentials.fromStream(keyFile))
                .build()
                .getService();

        String fileName = gcsRequest.getFile().getOriginalFilename();

        BlobInfo blobInfo = BlobInfo.newBuilder(BUCKET_NAME, fileName)
                .setContentType(gcsRequest.getFile().getContentType())
                .build();

        storage.create(blobInfo, gcsRequest.getFile().getInputStream());

        URL signedUrl = storage.signUrl(
                blobInfo,
                7, TimeUnit.DAYS,
                Storage.SignUrlOption.withV4Signature()
        );

        return signedUrl.toString();
    }
}