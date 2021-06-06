package com.project.securitybackend.util.exceptions;

import com.project.securitybackend.util.exceptions.CertificateExceptions.CertificateEmailAlreadyExistException;
import com.project.securitybackend.util.exceptions.CertificateExceptions.EmptyCertificateListException;
import com.project.securitybackend.util.exceptions.UserExceptions.BadCredentialsException;
import com.project.securitybackend.util.exceptions.UserExceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "User not found");
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CertificateEmailAlreadyExistException.class)
    public ResponseEntity<Object> handleCertificateEmailExistsException(CertificateEmailAlreadyExistException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Certificate with this email already exist");
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyCertificateListException.class)
    public ResponseEntity<Object> handleEmptyCertificateListException(EmptyCertificateListException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "There are no certificate requests.");
        return new ResponseEntity<>(body, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Bad credentials");
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

}
