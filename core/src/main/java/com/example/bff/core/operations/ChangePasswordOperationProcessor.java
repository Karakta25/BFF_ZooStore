package com.example.bff.core.operations;

import com.example.bff.api.operations.user.changePassword.ChangePasswordInput;
import com.example.bff.api.operations.user.changePassword.ChangePasswordOperation;
import com.example.bff.api.operations.user.changePassword.ChangePasswordOutput;
import com.example.bff.core.exceptions.NoSuchUserException;
import com.example.bff.core.exceptions.WrongUserEmailException;
import com.example.bff.core.exceptions.WrongUserPasswordException;
import com.example.bff.persistence.entities.User;
import com.example.bff.persistence.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChangePasswordOperationProcessor implements ChangePasswordOperation {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public ChangePasswordOutput process(ChangePasswordInput input) {

        User user = userRepository.findByEmail(input.getEmail()).orElseThrow(NoSuchUserException:: new);

        if(!input.getEmail().equals(user.getEmail()))
            throw new WrongUserEmailException();

        if(input.getOldPassword().isBlank())
            throw new WrongUserPasswordException();

        if(passwordEncoder.matches(input.getOldPassword(), user.getPassword())){
            if (!input.getNewPassword().isBlank())
                user.setPassword(passwordEncoder.encode(input.getNewPassword()));
        }

        user.setEmail(input.getEmail());

        userRepository.save(user);

        return ChangePasswordOutput.builder()
                .success(true)
                .build();
    }

}
