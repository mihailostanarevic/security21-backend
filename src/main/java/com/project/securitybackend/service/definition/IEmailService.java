package com.project.securitybackend.service.definition;

import com.project.securitybackend.entity.SimpleUser;

public interface IEmailService {

    void confirmationMail(SimpleUser simpleUser);

}
