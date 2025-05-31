package com.ps.auction.house.controllers;

import com.ps.auction.house.dtos.UserPostDTO;
import com.ps.auction.house.models.entities.User;
import com.ps.auction.house.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<User>> testUserService() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/emailAvailable")
    public ResponseEntity<Boolean> checkUserExists(@RequestParam String email) {
        boolean exists = userService.isEmailAvailable(email);
        return ResponseEntity.ok(exists);
    }

    @PostMapping(value = "/postUser", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Boolean> createUser(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("phoneNumber") Long phoneNumber,
            @RequestParam("address") String address,
            @RequestParam("district") String district,
            @RequestParam(value = "file", required = false) MultipartFile file
    ) throws IOException {
        //create DTO from params
        UserPostDTO userDTO = new UserPostDTO();
        userDTO.setFirstName(firstName);
        userDTO.setLastName(lastName);
        userDTO.setEmail(email);
        userDTO.setPassword(password);
        userDTO.setAddress(address);
        userDTO.setDistrict(district);
        userDTO.setProfilePicture(file);
        userDTO.setPhoneNumber(phoneNumber);

        userService.postUser(userDTO);
        return ResponseEntity.ok(true);
    }
}
