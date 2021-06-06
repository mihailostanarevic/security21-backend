package com.project.securitybackend.util.exceptions.UserExceptions;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(UUID id) {
        super(String.format("User with Id %s not found", id));
    }
}
