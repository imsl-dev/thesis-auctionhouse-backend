package com.ps.auction.house.dtos;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UserPostDTO {
    private String firstName;
    private String lastName;
    private Long phoneNumber;
    private String email;
    private String password;
    private String address;
    private String district;
    private MultipartFile profilePicture;
}
