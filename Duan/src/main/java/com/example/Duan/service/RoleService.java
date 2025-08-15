package com.example.Duan.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Duan.dto.request.RoleRequest;
import com.example.Duan.dto.response.RoleResponse;
import com.example.Duan.mapper.RoleMapper;
import com.example.Duan.repository.PermisionResponsitory;
import com.example.Duan.repository.RoleResponsitory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {
    RoleResponsitory roleResponsitory;
    RoleMapper roleMapper;
    PermisionResponsitory permisionResponsitory;

    public RoleResponse create(RoleRequest request) {
        var role = roleMapper.toRole(request);
        var permissions = permisionResponsitory.findAllById(request.getPermission());

        role.setPermissions(new HashSet<>(permissions));

        role = roleResponsitory.save(role);
        return roleMapper.toRoleResponse(role);
    }

    public List<RoleResponse> getAll() {
        return roleResponsitory.findAll().stream()
                .map(roleMapper::toRoleResponse)
                .toList();
    }

    public void delete(String role) {
        roleResponsitory.deleteById(role);
    }
}
