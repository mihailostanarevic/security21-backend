package com.project.securitybackend.controller;

import com.project.securitybackend.dto.request.LoginRequest;
import com.project.securitybackend.dto.response.UserResponse;
import com.project.securitybackend.service.definition.IAuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final IAuthService _loginService;

    public AuthController(IAuthService loginService) {
        _loginService = loginService;
    }

    @PostMapping("/login")
    public UserResponse login(@RequestBody LoginRequest request) {
        return _loginService.login(request);
    }
}
