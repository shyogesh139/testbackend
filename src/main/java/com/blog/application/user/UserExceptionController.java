package com.blog.application.user;

import com.blog.application.userexceptions.UserAlreadyExists;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionController extends RuntimeException {

    @ExceptionHandler(UserAlreadyExists.class)
    public ResponseEntity<?> UserAlreadyExists(UserAlreadyExists userAlreadyExists){
        return ResponseEntity.status(HttpStatusCode.valueOf(401)).body(userAlreadyExists.getMessage());
    }
}
