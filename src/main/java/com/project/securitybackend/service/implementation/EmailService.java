package com.project.securitybackend.service.implementation;

import com.project.securitybackend.config.EmailContext;
import com.project.securitybackend.entity.SimpleUser;
import com.project.securitybackend.service.definition.IEmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;

@Component
public class EmailService implements IEmailService {

    private final EmailContext _emailContext;

    public EmailService(EmailContext emailContext) {
        _emailContext = emailContext;
    }

    @Override
    public void confirmationMail(SimpleUser simpleUser) {
        String to = simpleUser.getUsername();
        String subject = "Confirm Account";
        Context context = new Context();
        String link = String.format("http://localhost:4200/account-confirmation/%s", simpleUser.getId());
        context.setVariable("name", String.format("%s %s", simpleUser.getFirstName(), simpleUser.getLastName()));
        context.setVariable("link", String.format("%s", link));
        _emailContext.send(to, subject, "confirmationMail", context);
    }
}