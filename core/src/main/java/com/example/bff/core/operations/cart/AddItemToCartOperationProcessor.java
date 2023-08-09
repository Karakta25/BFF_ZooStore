package com.example.bff.core.operations.cart;

import com.example.bff.api.operations.cart.addItemToCart.AddItemToCartInput;
import com.example.bff.api.operations.cart.addItemToCart.AddItemToCartOperation;
import com.example.bff.api.operations.cart.addItemToCart.AddItemToCartOutput;
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
public class AddItemToCartOperationProcessor implements AddItemToCartOperation {

    private final ZooStorageRestClient zooStorageRestClient;
    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;
    @Override
    public AddItemToCartOutput process(AddItemToCartInput input) {

        try{
            zooStorageRestClient.getItemFromStorage(input.getItemId());
        }
        catch(Exception e)
        { throw new NoSuchItemException();}

        GetItemStorageByIdOutput itemFromStorage = zooStorageRestClient.getItemFromStorage(input.getItemId());

        User user = getUserByEmail();

        Optional<CartItem> optionalCartItem = user.getCart().stream()
                .filter(item -> item.getItemId().equals(UUID.fromString(input.getItemId())))
                .findFirst();

        if(optionalCartItem.isPresent())
        {
            CartItem updatedCartItem = optionalCartItem.get();
            user.getCart().remove(updatedCartItem);
            updatedCartItem.setQuantity(updatedCartItem.getQuantity()+1);


//            if(itemFromStorage.getQuantity() < updatedCartItem.getQuantity())
//                throw new InsufficientAvailabilityException();

            Double totalPrice = updatedCartItem.getQuantity()*itemFromStorage.getPrice();

            user.getCart().add(updatedCartItem);
            cartItemRepository.save(updatedCartItem);

            return AddItemToCartOutput.builder()
                    .itemId(updatedCartItem.getItemId().toString())
                    .totalPrice(totalPrice)
                    .quantity(updatedCartItem.getQuantity())
                    .build();
        }


        Double totalPrice = input.getQuantity()*itemFromStorage.getPrice();

        CartItem cartItem = CartItem.builder()
                .itemId(UUID.fromString(itemFromStorage.getItemID()))
                .price(totalPrice)
                .quantity(input.getQuantity())
                .user(user)
                .build();

        cartItemRepository.save(cartItem);
        user.getCart().add(cartItem);
        userRepository.save(user);

        return AddItemToCartOutput.builder()
                .itemId(cartItem.getItemId().toString())
                .totalPrice(cartItem.getPrice())
                .quantity(cartItem.getQuantity())
                .build();
    }

    private User getUserByEmail()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByEmail(authentication.getName()).
                orElseThrow(NoSuchUserException::new);
    }
}
