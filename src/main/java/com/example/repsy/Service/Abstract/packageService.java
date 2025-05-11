package com.example.repsy.Service.Abstract;

import jakarta.annotation.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface packageService {
    void deploy(String packageName, String version, MultipartFile packageFile, MultipartFile metaFile) throws IOException;

    Resource download(String packageName, String version, String fileName);
}
