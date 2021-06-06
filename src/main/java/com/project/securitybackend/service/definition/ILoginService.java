package com.project.securitybackend.service.definition;

import com.project.securitybackend.dto.request.LoginRequest;
import com.project.securitybackend.dto.response.LoginResponse;

public interface ILoginService {

    LoginResponse login(LoginRequest request) throws Exception;
}
