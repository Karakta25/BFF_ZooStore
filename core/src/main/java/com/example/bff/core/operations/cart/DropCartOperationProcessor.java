package com.example.bff.core.operations.cart;

import com.example.bff.api.operations.cart.dropCart.DropCartInput;
import com.example.bff.api.operations.cart.dropCart.DropCartOperation;
import com.example.bff.api.operations.cart.dropCart.DropCartOutput;
import com.example.bff.core.exceptions.NoSuchUserException;
import com.example.bff.persistence.entities.CartItem;
import com.example.bff.persistence.entities.User;
import com.example.bff.persistence.repositories.CartItemRepository;
import com.example.bff.persistence.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DropCartOperationProcessor implements DropCartOperation {
    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;
    @Override
    public DropCartOutput process(DropCartInput input) {

        User user = getUserByEmail();

        cartItemRepository.deleteAll(user.getCart());
        user.getCart().clear();
        userRepository.save(user);

        return DropCartOutput.builder()
                .success(true).build();

    }

    private User getUserByEmail()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByEmail(authentication.getName()).
                orElseThrow(NoSuchUserException::new);
    }
}
