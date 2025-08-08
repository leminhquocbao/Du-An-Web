package com.example.Duan.controller;

import com.example.Duan.dto.request.ApiResponse;
import com.example.Duan.dto.request.PermissionRequest;
import com.example.Duan.dto.request.UserCreationRequest;
import com.example.Duan.dto.request.UserUpdateRequest;
import com.example.Duan.dto.response.PermissionResponse;
import com.example.Duan.dto.response.UserReponse;
import com.example.Duan.entity.Permission;
import com.example.Duan.service.PermisionService;
import com.example.Duan.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RestController
@RequestMapping("/permissions")
@Slf4j
public class PermissionController {
    PermisionService permisionService;

    @PostMapping
    ApiResponse<PermissionResponse> create(@RequestBody PermissionRequest request){

        return ApiResponse.<PermissionResponse>builder()
                .result(permisionService.create(request))
                .build();
    }
    @GetMapping
    ApiResponse<List<PermissionResponse>> getAll(){
        return ApiResponse.<List<PermissionResponse>>builder()
                .result(permisionService.getAll())
                .build();
    }
    @DeleteMapping("/{permission}")
    ApiResponse<Void> delete(@PathVariable String permission){
        permisionService.delete(permission);
        return ApiResponse.<Void>builder().build();
    }
}
