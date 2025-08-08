package com.example.Duan.service;

import com.example.Duan.dto.request.UserCreationRequest;
import com.example.Duan.dto.request.UserUpdateRequest;
import com.example.Duan.dto.response.UserReponse;
import com.example.Duan.entity.User;
import com.example.Duan.enums.Role;
import com.example.Duan.exception.AppException;
import com.example.Duan.exception.ErrorCode;
import com.example.Duan.mapper.UserMapper;
import com.example.Duan.repository.RoleResponsitory;
import com.example.Duan.repository.UserRepository;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Data
@Builder
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class UserService {
    private final RoleRepository roleRepository;
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    RoleResponsitory responsitory;
    public UserReponse createUser(UserCreationRequest request){
    log.info("Service: Create user");

        if (userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXITED);

        User user  = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());

        //user.setRoles(roles);

        return userMapper.toUserReponse(userRepository.save(user));
    }
    public UserReponse updateUser(String userId, UserUpdateRequest request){
        User user = userRepository.findById(userId)
               .orElseThrow(() -> new RuntimeException("User not found"));

       userMapper.updateUser(user, request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        var roles = roleRepository.findAllById(request.getRoles());
        user.setRoles(new HashSet<>(roles));
        return userMapper.toUserReponse(userRepository.save(user));
    }
    @PreAuthorize("hasRole('ADMIN')")
    // @PreAuthorize("hasAuthority('CREATE')")
    public List<UserReponse> getUsers(){
        log.info("In method get users");
        return userRepository.findAll().stream().map(userMapper::toUserReponse).toList();
    }
    @PostAuthorize("returnObject.username == authentication.name")
    public UserReponse getUser(String id){
        log.info("In method get user by id");
        return userMapper.toUserReponse(userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found")));
    }
    public void userDelete(String userId){
        userRepository.deleteById(userId);
    }

    public UserReponse getMyInfo() {
        var contex = SecurityContextHolder.getContext();
        String name = contex.getAuthentication().getName();

       User user = userRepository.findByUsername(name).orElseThrow(()
                -> new AppException(ErrorCode.USER_NOT_EXITED));

        return userMapper.toUserReponse(user);
    }
}
