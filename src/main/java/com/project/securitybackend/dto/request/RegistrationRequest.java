package com.project.securitybackend.dto.request;

import lombok.Data;

@Data
public class RegistrationRequest {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
}
