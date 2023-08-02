package com.example.bff.api.operations.user.logInUser;

import com.example.bff.api.operations.base.OperationResult;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogInUserOutput implements OperationResult {

    private String jwt;
}
