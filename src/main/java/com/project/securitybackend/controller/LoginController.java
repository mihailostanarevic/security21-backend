package com.project.securitybackend.controller;

import com.project.securitybackend.dto.request.LoginRequest;
import com.project.securitybackend.dto.response.LoginResponse;
import com.project.securitybackend.service.definition.ILoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginController {

    private final ILoginService _loginService;

    public LoginController(ILoginService loginService) {
        _loginService = loginService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return _loginService.login(request);
    }
}
