package com.project.securitybackend.controller;

import com.project.securitybackend.dto.response.SimpleUserResponse;
import com.project.securitybackend.service.definition.ISimpleUserService;
import com.project.securitybackend.util.enums.UserStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final ISimpleUserService _simpleUserService;

    public UserController(ISimpleUserService simpleUserService) {
        _simpleUserService = simpleUserService;
    }

    @GetMapping("/{userStatus}")
    public List<SimpleUserResponse> getUsersByStatus(@PathVariable("userStatus") UserStatus userStatus) {
        return _simpleUserService.getUsersByUserStatus(userStatus);
    }
}
