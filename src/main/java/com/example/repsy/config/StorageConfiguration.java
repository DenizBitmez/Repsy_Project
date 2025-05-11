package com.example.repsy.config;


import com.example.repsy.Storage.FileStorageService;
import com.example.repsy.Storage.ObjectStorageService;
import com.example.repsy.Storage.StorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfiguration {
    @Bean
    public StorageService storageService(
            @Value("${storage.strategy}") String strategy,
            FileStorageService fileSystemStorageService,
            ObjectStorageService objectStorageService
    ) {
        return switch (strategy) {
            case "file-system" -> fileSystemStorageService;
            case "object-storage" -> objectStorageService;
            default -> throw new IllegalArgumentException("Invalid storage strategy: " + strategy);
        };
    }

    @Bean
    public FileStorageService fileStorageService() {
        return new FileStorageService();
    }

}
