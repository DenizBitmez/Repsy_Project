package com.example.repsy.Storage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    void save(String path, MultipartFile file);
    FileSystemResource load(String path);
}
