package com.example.Duan.mapper;

import com.example.Duan.dto.request.PermissionRequest;
import com.example.Duan.dto.request.RoleRequest;
import com.example.Duan.dto.response.PermissionResponse;
import com.example.Duan.dto.response.RoleResponse;
import com.example.Duan.entity.Permission;
import com.example.Duan.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);

}
