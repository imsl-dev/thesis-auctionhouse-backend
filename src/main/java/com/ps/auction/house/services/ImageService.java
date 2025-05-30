package com.ps.auction.house.services;


import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

public class ImageService {

    //TODO since logic is extracted from userService then specify where file will be saved (users folder or auctions folder?)
    private final String uploadDir = "uploads/users";

    public boolean saveImage(MultipartFile imageFile) {
        try {
            //create directory to save if it doesn't exist
            File dir = new File(uploadDir);

            if (!dir.exists()) {dir.mkdirs();}

            //create unique file name

            String fileName = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, fileName);

            Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
