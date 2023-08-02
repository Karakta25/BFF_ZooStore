package com.example.bff.api.operations.user.logInUser;

import com.example.bff.api.operations.base.OperationInput;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogInUserInput implements OperationInput {

    @Email
    private String email;
    @NotBlank
    private String password;
}
