package com.ktgroup.application.dto;

import lombok.Data;

@Data
public class AppUserForm {
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private String confirmPassword;
    private String gender;
    private String email;
    private Long role;

}
