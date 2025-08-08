package com.example.Duan.mapper;

import com.example.Duan.dto.request.PermissionRequest;
import com.example.Duan.dto.request.UserCreationRequest;
import com.example.Duan.dto.request.UserUpdateRequest;
import com.example.Duan.dto.response.PermissionResponse;
import com.example.Duan.dto.response.UserReponse;
import com.example.Duan.entity.Permission;
import com.example.Duan.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionReponse(Permission permission);

}
