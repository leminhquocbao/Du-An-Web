package com.example.Duan.dto.request;

import com.example.Duan.validator.DobConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @Size(min = 4, message = "USERNAME_INVALID")
      String username;
    @Size(min = 8,message = "USERPASSWORD_INVALID")
     String password;
     String firstName;
     String lastName;

     @DobConstraint(min = 16, message = "INVALID_DOB")
     LocalDate dob;


}
