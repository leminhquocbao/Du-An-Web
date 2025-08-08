package com.example.Duan.service;

import com.example.Duan.dto.request.PermissionRequest;
import com.example.Duan.dto.response.PermissionResponse;
import com.example.Duan.entity.Permission;
import com.example.Duan.mapper.PermissionMapper;
import com.example.Duan.repository.PermisionResponsitory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermisionService {

    PermisionResponsitory permisionResponsitory;
    PermissionMapper permissionMapper;

  public PermissionResponse create(PermissionRequest request){
        Permission permission = permissionMapper.toPermission(request);
        permission = permisionResponsitory.save(permission);
        return  permissionMapper.toPermissionReponse(permission);
    }
   public List<PermissionResponse> getAll(){
        var permissions = permisionResponsitory.findAll();
        return permissions.stream().map(permissionMapper::toPermissionReponse).toList();
    }
   public void delete(String permission){
        permisionResponsitory.deleteById(permission);
    }
}
