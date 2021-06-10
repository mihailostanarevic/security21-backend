package com.project.securitybackend.dto.request;

import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class RecoverPasswordRequest {

    @Size(min = 8, max = 50, message = "The password must contain at least 8 characters")
    @Pattern(regexp = "^((?=.*\\d)(?=.*[A-Z])(?=.*\\W))$", message = "Your password must contain at least one uppercase letter, one number and one special character")
    private String password;

    @Size(min = 8, max = 50, message = "The password must contain at least 8 characters")
    @Pattern(regexp = "^((?=.*\\d)(?=.*[A-Z])(?=.*\\W))$", message = "Your password must contain at least one uppercase letter, one number and one special character")
    private String repeatedPassword;

    private String user;
}
