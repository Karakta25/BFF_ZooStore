package com.example.bff.api.operations.user.changePassword;

import com.example.bff.api.operations.base.OperationInput;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordInput implements OperationInput {

    @Email
    private String email;
    private String oldPassword;
    private String newPassword;


}
