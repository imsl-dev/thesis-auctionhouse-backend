package com.ps.auction.house.controllers;

import com.ps.auction.house.dtos.ErrorResponse;
import com.ps.auction.house.dtos.UserLoginDTO;
import com.ps.auction.house.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @GetMapping()
    public ResponseEntity<?> logIn(@RequestParam String email, @RequestParam String password) {
        UserLoginDTO dto = userService.logIn(email,password);
        if (dto.getFirstName() != null) {
            return ResponseEntity.ok(dto);
        }
        else {
            ErrorResponse error = new ErrorResponse("Invalid email or password", 400);
            return ResponseEntity.badRequest().body(error);
        }
        }

    }
