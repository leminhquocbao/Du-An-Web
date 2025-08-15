package com.example.Duan.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.Duan.dto.request.ApiResponse;
import com.example.Duan.dto.request.PermissionRequest;
import com.example.Duan.dto.response.PermissionResponse;
import com.example.Duan.service.PermisionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/permissions")
@Slf4j
public class PermissionController {
    PermisionService permisionService;

    @PostMapping
    ApiResponse<PermissionResponse> create(@RequestBody PermissionRequest request) {

        return ApiResponse.<PermissionResponse>builder()
                .result(permisionService.create(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<PermissionResponse>> getAll() {
        return ApiResponse.<List<PermissionResponse>>builder()
                .result(permisionService.getAll())
                .build();
    }

    @DeleteMapping("/{permission}")
    ApiResponse<Void> delete(@PathVariable String permission) {
        permisionService.delete(permission);
        return ApiResponse.<Void>builder().build();
    }
}
