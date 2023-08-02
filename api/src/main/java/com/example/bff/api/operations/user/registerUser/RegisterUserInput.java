package com.example.bff.api.operations.user.registerUser;

import com.example.bff.api.operations.base.OperationInput;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserInput implements OperationInput {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
}
