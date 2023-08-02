package com.example.bff.api.operations.user.registerUser;

import com.example.bff.api.operations.base.OperationResult;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserOutput implements OperationResult {

    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
}
