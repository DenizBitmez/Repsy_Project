package com.example.repsy.Storage;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.MinioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service("objectSystemStorageService")
public class ObjectStorageService implements StorageService {
    private final MinioClient minioClient;
    private final String bucket;

    public ObjectStorageService(@Value("${minio.url}") String miniourl,
                                @Value("${minio.accessKey}") String accessKey,
                                @Value("${minio.secretKey}") String secretKey,
                                @Value("${minio.bucket}") String bucket) {
        this.minioClient = MinioClient.builder()
                .endpoint(miniourl)
                .credentials(accessKey, secretKey)
                .build();
        this.bucket = bucket;
    }

    @Override
    public void save(String path, MultipartFile file) {
        try (InputStream is = file.getInputStream()) {
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucket)
                    .object(path)
                    .stream(is, file.getSize(), -1)
                    .contentType(file.getContentType()).build());
        } catch (MinioException | InvalidKeyException | NoSuchAlgorithmException e) {
            throw new IllegalStateException("The file cannot be uploaded to the internal storage. Please retry later", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public FileSystemResource load(String path) {
        try {
            InputStream file = minioClient.getObject(GetObjectArgs.builder()
                    .bucket(bucket)
                    .object(path).build());
            return new FileSystemResource("path-to-temporary-file");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
