package com.example.repsy.controller;

import com.example.repsy.Service.Abstract.packageService;
import jakarta.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/{packageName}/{version}")
public class packageController {
    private final packageService service;

    packageController(packageService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> deploy( @PathVariable String packageName,
                                          @PathVariable String version,
                                          @RequestPart("package") MultipartFile packageFile,
                                          @RequestPart("meta") MultipartFile metaFile) throws IOException {
        service.deploy(packageName, version, packageFile, metaFile);
        return ResponseEntity.ok("Package uploaded successfully.");
    }

    @GetMapping("/fileName")
    public ResponseEntity<Resource> download( @PathVariable String packageName,
                                            @PathVariable String version,
                                            @PathVariable String fileName){

        Resource file = service.download(packageName, version, fileName);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(file);
    }
}
