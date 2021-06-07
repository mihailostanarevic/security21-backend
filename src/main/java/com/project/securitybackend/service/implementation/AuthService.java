package com.project.securitybackend.service.implementation;

import com.project.securitybackend.dto.request.LoginRequest;
import com.project.securitybackend.dto.response.UserResponse;
import com.project.securitybackend.entity.SimpleUser;
import com.project.securitybackend.entity.User;
import com.project.securitybackend.entity.UserDetailsImpl;
import com.project.securitybackend.repository.IUserRepository;
import com.project.securitybackend.security.TokenUtils;
import com.project.securitybackend.service.definition.IAuthService;
import com.project.securitybackend.util.enums.UserRole;
import com.project.securitybackend.util.enums.UserStatus;
import com.project.securitybackend.util.exceptions.UserExceptions.BadCredentialsException;
import com.project.securitybackend.util.exceptions.UserExceptions.UserNotFoundException;
import com.project.securitybackend.util.exceptions.UserExceptions.UserRegistrationDeniedException;
import com.project.securitybackend.util.exceptions.UserExceptions.UserRegistrationNotApprovedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService {

    private final IUserRepository _userRepository;
    private final PasswordEncoder _passwordEncoder;
    private final AuthenticationManager _authenticationManager;
    private final TokenUtils _tokenUtils;

    public AuthService(IUserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenUtils tokenUtils) {
        _userRepository = userRepository;
        _passwordEncoder = passwordEncoder;
        _authenticationManager = authenticationManager;
        _tokenUtils = tokenUtils;
    }

    @Override
    public UserResponse login(LoginRequest request) {
        User user = _userRepository.findOneByUsername(request.getUsername());
        if(!isUserFound(user, request)) {
            throw new UserNotFoundException();
        }

        checkSimpleUserStatus(user);
        Authentication authentication = loginSimpleUser(request.getUsername(), request.getPassword());
        return createLoginUserResponse(authentication, user);
    }


    private boolean isUserFound(User user, LoginRequest request) {
        return user != null && _passwordEncoder.matches(request.getPassword(), user.getPassword());
    }

    private void checkSimpleUserStatus(User user) {
        if(user.getUserRole() == UserRole.SIMPLE_USER){
            if( ((SimpleUser)user).getUserStatus().equals(UserStatus.PENDING) ) {
                throw new UserRegistrationNotApprovedException();
            }
            if( ((SimpleUser)user).getUserStatus().equals(UserStatus.DENIED) ) {
                throw new UserRegistrationDeniedException();
            }
        }
    }

    private Authentication loginSimpleUser(String mail, String password) {
        Authentication authentication = null;
        try {
            authentication = _authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(mail, password));
        }catch (org.springframework.security.authentication.BadCredentialsException e){
            throw new BadCredentialsException();
        }catch (DisabledException e){
            throw new UserRegistrationNotApprovedException();
        }catch (Exception e) {
            e.printStackTrace();
        }

        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
        return authentication;
    }

    private UserResponse createLoginUserResponse(Authentication authentication, User user) {
        UserDetailsImpl userLog = (UserDetailsImpl) authentication.getPrincipal();
        String jwt = _tokenUtils.generateToken(userLog.getUsername());
        int expiresIn = _tokenUtils.getExpiredIn();

        UserResponse userResponse = mapUserToUserResponse(user);
        userResponse.setToken(jwt);
        userResponse.setTokenExpiresIn(expiresIn);

        return userResponse;
    }

    private UserResponse mapUserToUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());

        userResponse.setUsername(user.getUsername());
        userResponse.setUserRole(user.getUserRole().toString());
        return userResponse;
    }
}
