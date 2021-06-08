package com.project.securitybackend.service.definition;

import com.project.securitybackend.dto.request.LoginRequest;
import com.project.securitybackend.dto.request.RegistrationRequest;
import com.project.securitybackend.dto.response.RegistrationResponse;
import com.project.securitybackend.dto.response.UserResponse;

public interface IAuthService {

    UserResponse login(LoginRequest request);

    RegistrationResponse registration(RegistrationRequest request);
}
