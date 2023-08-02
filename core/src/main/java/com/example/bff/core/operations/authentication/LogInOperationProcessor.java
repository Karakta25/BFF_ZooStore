package com.example.bff.core.operations.authentication;

import com.example.bff.api.operations.user.logInUser.LogInUserInput;
import com.example.bff.api.operations.user.logInUser.LogInUserOperation;
import com.example.bff.api.operations.user.logInUser.LogInUserOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogInOperationProcessor implements LogInUserOperation {

    private final JwtService jwtService;
    @Override
    public LogInUserOutput process(LogInUserInput input) {

        return LogInUserOutput.builder().jwt(this.jwtService.generateJwt(input)).build();
    }
}
