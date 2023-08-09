package com.example.bff.core.operations;

import com.example.bff.api.operations.payment.PaymentInput;
import com.example.bff.api.operations.payment.PaymentOperation;
import com.example.bff.api.operations.payment.PaymentOutput;
import com.example.bff.core.exceptions.NoSuchUserException;
import com.example.bff.persistence.entities.User;
import com.example.bff.persistence.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentOperationProcessor implements PaymentOperation {
    private final UserRepository userRepository;
    @Override
    public PaymentOutput process(PaymentInput input) {
        return PaymentOutput
                .builder()
                .userId(getUserByEmail().getId().toString())
                .success(true)
                .build();
    }


    private User getUserByEmail()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByEmail(authentication.getName()).
                orElseThrow(NoSuchUserException::new);
    }
}
