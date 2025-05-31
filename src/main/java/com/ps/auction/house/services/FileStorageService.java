package com.ps.auction.house.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

@Service
public class FileStorageService {

    private static final String UPLOAD_DIR = "uploads/";

    public String saveProfilePicture(MultipartFile file, String filenamePrefix) throws IOException {
        // Ensure upload directory exists
        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String filename;
        Path filePath;

        if (file == null || file.isEmpty()) {
            // Use default image if no file is uploaded
            String extension = ".webp";
            filename = filenamePrefix + System.currentTimeMillis() + extension;
            filePath = uploadPath.resolve(filename);

            // Copy the default image to a new file for this user
            Path defaultImagePath = uploadPath.resolve("user.webp");
            Files.copy(defaultImagePath, filePath, StandardCopyOption.REPLACE_EXISTING);
        } else {
            // Create a unique filename using the original file extension
            String originalFilename = file.getOriginalFilename();
            String extension = "";

            if (originalFilename != null && originalFilename.contains(".")) {
                extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }

            filename = filenamePrefix + System.currentTimeMillis() + extension;
            filePath = uploadPath.resolve(filename);

            // Save the uploaded file
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        }

        // Return the relative path for storing in DB
        return filePath.toString();
    }
}