package com.example.repsy.config;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MinIoConfiguration {

    @Value("${storage.minio.url}")
    private String minioUrl;

    @Value("${storage.minio.accessKey}")
    private String accessKey;

    @Value("${storage.minio.secretKey}")
    private String secretKey;

    @Value("${storage.minio.bucket}")
    private String bucket;

}

