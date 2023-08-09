package com.example.bff.core;

import com.example.bff.api.operations.orderConfirmation.OrderConfirmationInput;
import com.example.bff.api.operations.orderConfirmation.OrderConfirmationOperation;
import com.example.bff.api.operations.orderConfirmation.OrderConfirmationOutput;


import com.example.bff.api.operations.payment.PaymentInput;
import com.example.bff.api.operations.payment.PaymentOperation;
import com.example.bff.api.operations.payment.PaymentOutput;
import com.example.bff.core.exceptions.InsufficientFundsException;
import com.example.bff.core.exceptions.NoSuchUserException;
import com.example.bff.persistence.entities.CartItem;
import com.example.bff.persistence.entities.User;
import com.example.bff.persistence.repositories.CartItemRepository;
import com.example.bff.persistence.repositories.UserRepository;
import com.example.zoostoreproject.api.operations.item.getCartItemProperties.GetCartItemPropertiesInput;
import com.example.zoostorestorage.api.operations.itemStorage.sale.CartItemInput;
import com.example.zoostorestorage.api.operations.itemStorage.sale.SaleInput;
import com.example.zoostorestorage.api.operations.itemStorage.sale.SaleOutput;
import com.example.zoostorestorage.restExport.ZooStorageRestClient;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class OrderConfirmationOperationProcessor implements OrderConfirmationOperation {

    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final ZooStorageRestClient storageRestClient;
    private final PaymentOperation paymentOperation;
    @Override
    public OrderConfirmationOutput process(OrderConfirmationInput input) {

        User user = getUserByEmail();

        List<CartItem> items = cartItemRepository.findCartItemsByUser(user);
        List<CartItemInput> cartItemInputs = mapCartItemListToCartItemListInput(items);

        SaleOutput sale = storageRestClient.sale(SaleInput.builder()
                        .userId(user.getId())
                        .items(cartItemInputs)
                        .build());

        PaymentInput paymentInput = PaymentInput.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .cardID("2142142")
                .build();

        PaymentOutput payment = paymentOperation.process(paymentInput);

        if(!payment.isSuccess())
            throw new InsufficientFundsException();

        return OrderConfirmationOutput.builder()
                .totalPrice(sale.getTotalPrice())
                .build();
    }

    private CartItemInput mapCartItemEntityToCartItemInput(CartItem cartItem)
    {
        return CartItemInput.builder()
                .itemId(cartItem.getItemId())
                .userId(cartItem.getUser().getId())
                .price(cartItem.getPrice())
                .quantity(cartItem.getQuantity())
                .build();
    }

    private List<CartItemInput>mapCartItemListToCartItemListInput(List<CartItem> cartItem) {
        return cartItem.stream()
                .map(this::mapCartItemEntityToCartItemInput)
                .toList();
    }
    private User getUserByEmail()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByEmail(authentication.getName()).
                orElseThrow(NoSuchUserException::new);
    }
}
