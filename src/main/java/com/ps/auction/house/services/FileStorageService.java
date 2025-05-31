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

        // If the file is null or empty, return the path to the default image
        if (file == null || file.isEmpty()) {
            return UPLOAD_DIR + "user.webp"; // default image
        }

        // Create a unique filename using the original file extension
        String originalFilename = file.getOriginalFilename();
        String extension = "";

        if (originalFilename != null && originalFilename.contains(".")) {
            extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        }

        String filename = filenamePrefix + System.currentTimeMillis() + extension;
        Path filePath = uploadPath.resolve(filename);

        // Save the file to disk
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // Return the relative path or filename for storing in DB
        return filePath.toString();
    }
}