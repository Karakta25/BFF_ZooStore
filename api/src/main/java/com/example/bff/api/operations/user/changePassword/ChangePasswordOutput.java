package com.example.bff.api.operations.user.changePassword;

import com.example.bff.api.operations.base.OperationResult;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordOutput implements OperationResult {

    private boolean success;

}
