package com.project.securitybackend.service.implementation;

import com.project.securitybackend.dto.request.LoginRequest;
import com.project.securitybackend.dto.response.AdminResponse;
import com.project.securitybackend.dto.response.LoginResponse;
import com.project.securitybackend.entity.Admin;
import com.project.securitybackend.repository.IAdminRepository;
import com.project.securitybackend.service.definition.ILoginService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements ILoginService {

    private final IAdminRepository _adminRepository;

    private final PasswordEncoder _passwordEncoder;

    public LoginService(IAdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        _adminRepository = adminRepository;
        _passwordEncoder = passwordEncoder;
    }

    @Override
    public LoginResponse login(LoginRequest request) throws Exception {
        Admin admin = _adminRepository.findOneByEmail(request.getEmail());

        if (admin == null) {
            throw new Exception(String.format("Bad credentials."));
        }

        if (!_passwordEncoder.matches(request.getPassword(), admin.getPassword())) {
            throw new Exception("Bad credentials.");
        }

        LoginResponse loginResponse = new LoginResponse();
        AdminResponse adminResponse = new AdminResponse();
        adminResponse.setId(admin.getId());
        adminResponse.setEmail(admin.getEmail());
        loginResponse.setAdmin(adminResponse);

        return loginResponse;
    }
}
