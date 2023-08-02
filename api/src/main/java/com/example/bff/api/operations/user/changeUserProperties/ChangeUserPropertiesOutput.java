package com.example.bff.api.operations.user.changeUserProperties;

import com.example.bff.api.operations.base.OperationResult;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChangeUserPropertiesOutput implements OperationResult {

    private String name;
    private String phone;
    private String email;
}
