package com.blog.application.user;

import com.blog.application.user.IO.ApiResponse;
import com.blog.application.userexceptions.UserAlreadyExists;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionController extends RuntimeException {

    @ExceptionHandler(UserAlreadyExists.class)
    public ResponseEntity<ApiResponse> UserAlreadyExists(UserAlreadyExists userAlreadyExists){
        ApiResponse apiResponse = new ApiResponse(
                "Already Exists",
                userAlreadyExists.getMessage()

        );
        return ResponseEntity.status(HttpStatusCode.valueOf(401)).body(apiResponse);
    }
}
