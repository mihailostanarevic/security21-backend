package com.project.securitybackend.service.definition;

import com.project.securitybackend.dto.response.CertificateRequestResponse;
import com.project.securitybackend.dto.response.SimpleUserResponse;
import com.project.securitybackend.util.enums.UserStatus;

import java.util.List;

public interface ISimpleUserService {

    List<SimpleUserResponse> getUsersByUserStatus(UserStatus userStatus);
}
