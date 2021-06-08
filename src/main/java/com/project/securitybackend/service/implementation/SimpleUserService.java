package com.project.securitybackend.service.implementation;

import com.project.securitybackend.dto.response.SimpleUserResponse;
import com.project.securitybackend.entity.SimpleUser;
import com.project.securitybackend.repository.ISimpleUserRepository;
import com.project.securitybackend.service.definition.ISimpleUserService;
import com.project.securitybackend.util.enums.UserStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class SimpleUserService implements ISimpleUserService {

    private final ISimpleUserRepository _simpleUserRepository;

    public SimpleUserService(ISimpleUserRepository simpleUserRepository) {
        _simpleUserRepository = simpleUserRepository;
    }

    @Override
    public List<SimpleUserResponse> getUsersByUserStatus(UserStatus userStatus) {
        List<SimpleUser> simpleUsersFilteredByStatus = _simpleUserRepository.findByUserStatus(userStatus);
        return mapSimpleUsersToSimpleUsersResponse(simpleUsersFilteredByStatus);
    }

    private List<SimpleUserResponse> mapSimpleUsersToSimpleUsersResponse(List<SimpleUser> simpleUsers) {
        List<SimpleUserResponse> retList = new ArrayList<>();
        for (SimpleUser simpleUser : simpleUsers) {
            retList.add(mapSimpleUserToSimpleUserResponse(simpleUser));
        }

        return retList;
    }

    private SimpleUserResponse mapSimpleUserToSimpleUserResponse(SimpleUser simpleUser) {
        SimpleUserResponse retSimpleUser = new SimpleUserResponse();
        retSimpleUser.setUsername(simpleUser.getUsername());
        retSimpleUser.setFirstName(simpleUser.getFirstName());
        retSimpleUser.setLastName(simpleUser.getLastName());
        retSimpleUser.setConfirmationDate(formatDate(simpleUser.getConfirmationTime()));
        return retSimpleUser;
    }

    private String formatDate(LocalDateTime confirmationTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return confirmationTime.format(formatter);
    }

}

