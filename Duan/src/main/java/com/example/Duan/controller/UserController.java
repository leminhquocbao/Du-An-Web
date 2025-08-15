package com.example.Duan.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.example.Duan.dto.request.ApiResponse;
import com.example.Duan.dto.request.UserCreationRequest;
import com.example.Duan.dto.request.UserUpdateRequest;
import com.example.Duan.dto.response.UserReponse;
import com.example.Duan.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    ApiResponse<UserReponse> createUser(@RequestBody @Valid UserCreationRequest request) {
        log.info("Controller: create user");
        ApiResponse<UserReponse> apiResponse = new ApiResponse<>();

        apiResponse.setResult(userService.createUser(request));

        return apiResponse;
    }

    @GetMapping
    ApiResponse<List<UserReponse>> getUser() {

        var authentocation = SecurityContextHolder.getContext().getAuthentication();
        log.info("Username: {}", authentocation.getName());
        authentocation.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));

        return ApiResponse.<List<UserReponse>>builder()
                .result(userService.getUsers())
                .build();
    }

    @GetMapping("/{userId}")
    ApiResponse<UserReponse> getUser(@PathVariable("userId") String userId) {
        return ApiResponse.<UserReponse>builder()
                .result(userService.getUser(userId))
                .build();
    }

    @GetMapping("/myInfo")
    ApiResponse<UserReponse> getMyInfo() {
        return ApiResponse.<UserReponse>builder()
                .result(userService.getMyInfo())
                .build();
    }

    @PutMapping("/{userId}")
    UserReponse updateUser(@PathVariable String userId, @RequestBody UserUpdateRequest request) {
        return userService.updateUser(userId, request);
    }

    @DeleteMapping("/{userId}")
    String userDelete(@PathVariable String userId) {
        userService.userDelete(userId);
        return "userid has been deleted";
    }
}
