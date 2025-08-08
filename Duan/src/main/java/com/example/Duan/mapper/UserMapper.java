package com.example.Duan.mapper;

import com.example.Duan.dto.request.UserCreationRequest;
import com.example.Duan.dto.request.UserUpdateRequest;
import com.example.Duan.dto.response.UserReponse;
import com.example.Duan.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);
    UserReponse toUserReponse(User user);
    @Mapping(target = "roles",ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
