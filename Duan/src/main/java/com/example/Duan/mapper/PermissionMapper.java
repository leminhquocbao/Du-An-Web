package com.example.Duan.mapper;

import org.mapstruct.Mapper;

import com.example.Duan.dto.request.PermissionRequest;
import com.example.Duan.dto.response.PermissionResponse;
import com.example.Duan.entity.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionReponse(Permission permission);
}
