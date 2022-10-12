package com.spring.jobapp.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.spring.jobapp.exception.ItemNotFoundException;
import com.spring.jobapp.repo.ICandidateRepo;

@Service
public class StorageService implements IStorageService {

    private final Path root = Paths.get("uploads");
    private Path rootFileDel = this.root;
    @Autowired
    private ICandidateRepo candidateRepo;
    
    @Override
    public void init() {
        try {
            Files.createDirectory(root);
        } catch (Exception e) {
        	
        }
        
    }

    @Override
    public void store(MultipartFile file, long candidateId) {
        try {
            String originalFileName = file.getOriginalFilename();
            String fileExtension = "." + originalFileName.substring(originalFileName.lastIndexOf(".")+1);
            boolean test = Files.deleteIfExists(this.rootFileDel.resolve(candidateId + fileExtension));
            Files.copy(file.getInputStream(), this.root.resolve(candidateId + fileExtension));
          } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
          }
        
    }

    @Override
    public Resource load(String filename) {
        try {
        	if(!candidateRepo.existsById(Long.parseLong(filename))) {
    			throw new ItemNotFoundException("item Candidate not found for id");
    		}
            Path file = root.resolve(filename + ".pdf");
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
              return resource;
            } else {
              throw new RuntimeException("Could not read the file!");
            }
          } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
          }
    }


    @Override
    public void deleteAll() {
        // TODO Auto-generated method stub
        FileSystemUtils.deleteRecursively(root.toFile());
        
    }
    
}
