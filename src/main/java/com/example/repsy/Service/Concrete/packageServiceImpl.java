package com.example.repsy.Service.Concrete;

import com.example.repsy.Entity.Package;
import com.example.repsy.Repository.packageRepository;
import com.example.repsy.Service.Abstract.packageService;
import com.example.repsy.Storage.StorageService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class packageServiceImpl implements packageService {
    private final packageRepository repository;
    private final StorageService storageService;

    public packageServiceImpl(packageRepository repository,StorageService storageService) {
        this.repository = repository;
        this.storageService = storageService;
    }

    @Override
    public void deploy(String packageName, String version, MultipartFile packageFile, MultipartFile metaFile) throws IOException {

            if (packageFile.isEmpty() && metaFile.isEmpty()){
             throw new IOException("File is empty");}

            String path= packageName+"/"+version;
            storageService.save(path+"/package.rep", packageFile);
            storageService.save(path + "/meta.json", metaFile);

            Package pkg = new Package(packageName, version, LocalDateTime.now());
            repository.save(pkg);
    }

    @Override
    public Resource download(String packageName, String version, String fileName){
        return (Resource) storageService.load(packageName + "/" + version + "/" + fileName);
    }
}

