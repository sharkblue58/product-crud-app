package com.crud.demo_app.services;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



@Service
public class ImageService {
    private final String uploadDir = "public/images/";
    public String storeImageOnDisk(MultipartFile img) {
        MultipartFile image = img;
        Date createdAt = new Date();
        String storageFileName = createdAt.getTime() + "-" + image.getOriginalFilename();
        try {
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            try (InputStream inputStream = image.getInputStream()) {
                Files.copy(inputStream, Paths.get(uploadDir + storageFileName), StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return storageFileName.toString();
    }

    public void deleteImageOnDisk(String img) {
       Path oldImagePath = Paths.get(uploadDir + img);
       try {
        Files.deleteIfExists(oldImagePath);
       } catch (Exception e) {
        System.out.println("Exception: " + e.getMessage());
       }
    }
    
}
