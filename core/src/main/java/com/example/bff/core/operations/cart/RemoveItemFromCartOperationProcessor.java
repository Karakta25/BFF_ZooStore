package com.example.bff.core.operations.cart;

import com.example.bff.api.operations.cart.removeItemFromCart.RemoveItemFromCartInput;
import com.example.bff.api.operations.cart.removeItemFromCart.RemoveItemFromCartOperation;
import com.example.bff.api.operations.cart.removeItemFromCart.RemoveItemFromCartOutput;
import com.example.bff.core.exceptions.NoSuchItemException;
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
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RemoveItemFromCartOperationProcessor implements RemoveItemFromCartOperation {

    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    @Override
    public RemoveItemFromCartOutput process(RemoveItemFromCartInput input) {

        User user = getUserByEmail();

        List<CartItem> cartItem = user.getCart()
                                    .stream()
                                    .filter(item -> item.getItemId().equals(UUID.fromString(input.getItemId())))
                                    .toList();

        cartItemRepository.deleteAll(cartItem);

        return RemoveItemFromCartOutput.builder()
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
