package com.example.bff.api.operations.user.changeUserProperties;

import com.example.bff.api.operations.base.OperationInput;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChangeUserPropertiesInput implements OperationInput {

    private String name;
    private String phone;
    private String email;
}
