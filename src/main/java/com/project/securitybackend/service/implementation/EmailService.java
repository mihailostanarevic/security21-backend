package com.project.securitybackend.service.implementation;

import com.project.securitybackend.security.TokenUtils;
import com.project.securitybackend.service.definition.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

@Service
public class EmailService implements IEmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private TokenUtils tokenUtils;

    public ResponseEntity<?> sendPasswordRecoveryEmail(String subject, String to, String from, String name) throws MessagingException {
        Context context = new Context();
        context.setVariable("user", name);
        String hash = tokenUtils.generateToken(to);
        context.setVariable("link", "http://localhost:4200/recover-password/" + hash);

        String body = templateEngine.process("passwordRecovery", context);
        sendMail(subject, to, from, body);

        return new ResponseEntity<>("We have sent a email to the account you provided, go check your email", HttpStatus.OK);
    }

    @Override
    public void sendMail(String subject, String to, String from, String body) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject(subject);
        helper.setText(body, true);
        helper.setTo("ms.mishoni@gmail.com");//staviti ovde to
        helper.setSentDate(new Date());

        javaMailSender.send(mimeMessage);
    }
}
