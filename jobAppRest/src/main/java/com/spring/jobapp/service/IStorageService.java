package com.spring.jobapp.service;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;


public interface IStorageService {
    public void init();

    public void store(MultipartFile file, long candidateId);

    public Resource load(String filename);

    public void deleteAll();

    
}
