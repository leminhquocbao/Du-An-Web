package com.example.Duan.service;

import com.example.Duan.dto.request.UserCreationRequest;
import com.example.Duan.dto.response.UserReponse;
import com.example.Duan.entity.User;
import com.example.Duan.exception.AppException;
import com.example.Duan.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.*;
import  static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertThrows;
@SpringBootTest
@Slf4j
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
    void initData(){
        dob = LocalDate.of(1990,1,1);
        request = UserCreationRequest.builder()
                .username("join")
                .firstName("Join")
                .lastName("Peter")
                .password("12345678")
                .dob(dob)
                .build();

        userReponse= UserReponse.builder()
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
    void createuse_validRequest_success(){
        //GIVEN

        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.save(any())).thenReturn(user);

        //WHEN
        var response = userService.createUser(request);

        //THEN
        Assertions.assertThat(response.getId()).isEqualTo("c5b3f6a7");
        Assertions.assertThat(response.getUsername()).isEqualTo("join");

    }
    @Test
    void createUser_userExisted_success(){
        //GIVEN

        when(userRepository.existsByUsername(anyString())).thenReturn(true);
        //when(userRepository.save(any())).thenReturn(user);

        //WHEN
      var exception = assertThrows(AppException.class, () -> userService.createUser(request));

        //THEN
        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1002);

    }


}
