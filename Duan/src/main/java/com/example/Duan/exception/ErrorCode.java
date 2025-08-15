package com.example.Duan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.Getter;

@Getter
public enum ErrorCode {
    INVALID_KEY(1001, "KEY ERROR", HttpStatus.BAD_REQUEST),
    USER_EXITED(1002, "User Exited", HttpStatus.BAD_REQUEST),
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized Exception", HttpStatus.INTERNAL_SERVER_ERROR),
    USERNAME_INVALID(1003, "Username must be at {min} characters", HttpStatus.BAD_REQUEST),
    USERPASSWORD_INVALID(1004, "Password must be at {min} characters", HttpStatus.BAD_REQUEST),
    AUTHENTICATED(1006, "Authenticated", HttpStatus.NOT_FOUND),
    UNAUTHORIZED(1007, "you don have permit denied", HttpStatus.FORBIDDEN),
    INVALID_DOB(1008, "Your age must be at least {min}", HttpStatus.BAD_REQUEST),
    USER_NOT_EXITED(1005, "User not Exited", HttpStatus.UNAUTHORIZED),
    USERNOTFOUN(1000, "USER NOT DOUNF", HttpStatus.BAD_REQUEST);

    private int code;
    private String message;
    private HttpStatusCode statusCode;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }
}
