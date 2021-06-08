package com.project.securitybackend.dto.response;

import lombok.Data;

@Data
public class SimpleUserResponse {

    private String username;
    private String firstName;
    private String lastName;
    private String confirmationDate;
}
