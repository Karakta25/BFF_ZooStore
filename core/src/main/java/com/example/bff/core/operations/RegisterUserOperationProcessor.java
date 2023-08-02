package com.example.bff.core.operations;

import com.example.bff.api.operations.user.registerUser.RegisterUserInput;
import com.example.bff.api.operations.user.registerUser.RegisterUserOperation;
import com.example.bff.api.operations.user.registerUser.RegisterUserOutput;
import com.example.bff.core.exceptions.UserAlreadyExistsException;
import com.example.bff.persistence.entities.User;
import com.example.bff.persistence.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterUserOperationProcessor implements RegisterUserOperation{

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public RegisterUserOutput process(RegisterUserInput input) {

        if(userRepository.findByEmail(input.getEmail()).isPresent())
                throw new UserAlreadyExistsException();

        User user = User.builder()
                .email(input.getEmail())
                .password(passwordEncoder.encode(input.getPassword()))
                .firstName(input.getFirstName())
                .lastName(input.getLastName())
                .phoneNumber(input.getPhoneNumber())
                .build();

        userRepository.save(user);

        return RegisterUserOutput.builder()
                .id(user.getId().toString())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }

}
