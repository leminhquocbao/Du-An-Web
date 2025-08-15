package com.example.Duan.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;

import com.example.Duan.dto.request.UserCreationRequest;
import com.example.Duan.dto.response.UserReponse;
import com.example.Duan.entity.User;
import com.example.Duan.exception.AppException;
import com.example.Duan.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@TestPropertySource("/test.properties")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    private UserCreationRequest request;
    private User user;
    private UserReponse userReponse;

    private LocalDate dob;

    @BeforeEach
    void initData() {
        dob = LocalDate.of(1990, 1, 1);
        request = UserCreationRequest.builder()
                .username("join")
                .firstName("Join")
                .lastName("Peter")
                .password("12345678")
                .dob(dob)
                .build();

        userReponse = UserReponse.builder()
                .id("c5b3f6a7")
                .username("join")
                .firstName("Join")
                .lastName("Peter")
                .dob(dob)
                .build();
        user = User.builder()
                .id("c5b3f6a7")
                .username("join")
                .firstName("Join")
                .lastName("Peter")
                .dob(dob)
                .build();
    }

    @Test
    void createuse_validRequest_success() {
        // GIVEN

        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.save(any())).thenReturn(user);

        // WHEN
        var response = userService.createUser(request);

        // THEN
        Assertions.assertThat(response.getId()).isEqualTo("c5b3f6a7");
        Assertions.assertThat(response.getUsername()).isEqualTo("join");
    }

    @Test
    void createUser_userExisted_success() {
        // GIVEN

        when(userRepository.existsByUsername(anyString())).thenReturn(true);
        // when(userRepository.save(any())).thenReturn(user);

        // WHEN
        var exception = assertThrows(AppException.class, () -> userService.createUser(request));

        // THEN
        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1002);
    }

    @Test
    @WithMockUser(username = "join")
    void getMyInfo_valid_success() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));

        var response = userService.getMyInfo();

        Assertions.assertThat(response.getUsername()).isEqualTo("join");
        Assertions.assertThat(response.getId()).isEqualTo("c5b3f6a7");
    }

    @Test
    @WithMockUser(username = "join")
    void getMyInfo_userNotFound_error() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.ofNullable(null));

        var exception = assertThrows(AppException.class, () -> userService.getMyInfo());

        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1005);
    }
}
