package com.ps.auction.house.controllers;

import com.ps.auction.house.models.entities.User;
import com.ps.auction.house.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
