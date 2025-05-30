package com.ps.auction.house.services;

import com.ps.auction.house.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public String testMethod() {
        return "Working as intended.";
    }
//
//    public Boolean saveUserImage(Long userId, MultipartFile imageFile) {
//
//        try {
//            //create directory to save if it doesn't exist
//            File dir = new File(uploadDir);
//
//            if (!dir.exists()) {dir.mkdirs();}
//
//            //create unique file name
//
//            String fileName = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
//            Path filePath = Paths.get(uploadDir, fileName);
//
//            Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return true;
//    }
}
