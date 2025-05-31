package com.ps.auction.house.dtos;

import com.ps.auction.house.models.enums.Role;
import lombok.Data;

@Data

public class UserLoginDTO {
    private String firstName;

    private String lastName;

    private String email;

    private Role role;

    private Long phoneNumber;

    private String address;

    private String district;

    private String imagePath;
}
