package com.example.bff.core.operations.cart;


import com.example.bff.api.operations.cart.changeItemQuantity.ChangeItemQuantityInput;
import com.example.bff.api.operations.cart.changeItemQuantity.ChangeItemQuantityOperation;
import com.example.bff.api.operations.cart.changeItemQuantity.ChangeItemQuantityOutput;
import com.example.bff.core.exceptions.InsufficientAvailabilityException;
import com.example.bff.core.exceptions.NoSuchItemException;
import com.example.bff.core.exceptions.NoSuchUserException;
import com.example.bff.persistence.entities.CartItem;
import com.example.bff.persistence.entities.User;
import com.example.bff.persistence.repositories.CartItemRepository;
import com.example.bff.persistence.repositories.UserRepository;
import com.example.zoostorestorage.api.operations.itemStorage.getItemById.GetItemStorageByIdOutput;
import com.example.zoostorestorage.restExport.ZooStorageRestClient;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChangeItemQuantityOperationProcessor implements ChangeItemQuantityOperation {

    private final ZooStorageRestClient zooStorageRestClient;
    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;
    @Override
    public ChangeItemQuantityOutput process(ChangeItemQuantityInput input) {

        try {
            zooStorageRestClient.getItemFromStorage(input.getItemId());
        }
        catch(Exception e)
        {
            throw new NoSuchItemException();
        }

        GetItemStorageByIdOutput itemFromStorage = zooStorageRestClient.getItemFromStorage(input.getItemId());

        User user = getUserByEmail();

        CartItem updatedCartItem = user.getCart().stream()
                .filter(item -> item.getItemId().equals(UUID.fromString(input.getItemId())))
                .findFirst().orElseThrow(NoSuchItemException::new);

        user.getCart().remove(updatedCartItem);
        updatedCartItem.setQuantity(input.getQuantity());


        if(itemFromStorage.getQuantity() < updatedCartItem.getQuantity())
            throw new InsufficientAvailabilityException();

        Double totalPrice = updatedCartItem.getQuantity()*itemFromStorage.getPrice();
        updatedCartItem.setPrice(totalPrice);

        user.getCart().add(updatedCartItem);
        cartItemRepository.save(updatedCartItem);
        userRepository.save(user);

        return ChangeItemQuantityOutput.builder()
                .itemId(updatedCartItem.getItemId().toString())
                .quantity(updatedCartItem.getQuantity())
                .build();
    }

    private User getUserByEmail()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByEmail(authentication.getName()).
                orElseThrow(NoSuchUserException::new);
    }
}
