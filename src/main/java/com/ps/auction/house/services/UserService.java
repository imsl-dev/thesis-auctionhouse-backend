package com.ps.auction.house.services;

import com.ps.auction.house.dtos.UserPostDTO;
import com.ps.auction.house.models.entities.User;
import com.ps.auction.house.models.enums.Role;
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
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final FileStorageService fileStorageService;


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean isEmailAvailable(String email) {
        User userWithEmail = userRepository.findByEmail(email);
        if (userWithEmail != null) {
            return false;
        }
        else return true;
    }

    public User postUser(UserPostDTO userDTO) throws IOException {
        // save the image and get the file location
        String location = fileStorageService.saveProfilePicture(userDTO.getProfilePicture(), userDTO.getLastName());


        User newUser = new User();
        newUser.setFirstName(userDTO.getFirstName());
        newUser.setLastName((userDTO.getLastName()));
        newUser.setEmail(userDTO.getEmail());
        newUser.setAddress(userDTO.getAddress());
        newUser.setDistrict(userDTO.getDistrict());
        newUser.setRole(Role.BUYER);
        newUser.setPhoneNumber(userDTO.getPhoneNumber());
        newUser.setImagePath(location);

        userRepository.save(newUser);
        return newUser;

    }


}
