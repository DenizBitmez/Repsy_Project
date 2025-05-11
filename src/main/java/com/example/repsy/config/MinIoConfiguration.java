package com.example.repsy.config;

import com.example.repsy.Storage.ObjectStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MinIoConfiguration {

    @Value("${storage.minio.url}")
    private String minioUrl;

    @Value("${storage.minio.access-key}")
    private String accessKey;

    @Value("${storage.minio.secret-key}")
    private String secretKey;

    @Value("${storage.minio.bucket}")
    private String bucket;

    @Bean
    public ObjectStorageService objectStorageService() {
        return new ObjectStorageService(minioUrl, accessKey, secretKey, bucket);
    }
}

