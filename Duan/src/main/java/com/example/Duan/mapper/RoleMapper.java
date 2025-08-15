package com.example.Duan.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.Duan.dto.request.RoleRequest;
import com.example.Duan.dto.response.RoleResponse;
import com.example.Duan.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
