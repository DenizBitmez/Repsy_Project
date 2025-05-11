package com.example.repsy.Storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service("fileSystemStorageService")
public class FileStorageService implements StorageService {
    @Value("${storage.path:/tmp/packages}")
    private String basePath;

    @Override
    public void save(String path, MultipartFile file) {
      try{
    Path path1 = Paths.get(basePath + path);
    Files.createDirectories(path1.getParent());
    file.transferTo(path1.toFile());
   }
       catch (IOException ex){
    throw new RuntimeException(ex);
    }
    }

    public FileSystemResource load(String path){
        Path filePath = Paths.get(basePath, path);
        return new FileSystemResource(filePath);
    }
}
