package com.example.bff.core.operations.cart;

import com.example.bff.api.operations.cart.getAllItemsFromCart.GetAllItemsFromCartInput;
import com.example.bff.api.operations.cart.getAllItemsFromCart.GetAllItemsFromCartOperation;
import com.example.bff.api.operations.cart.getAllItemsFromCart.GetAllItemsFromCartOutput;
import com.example.bff.api.operations.item.getItemProperties.GetFullItemPropertiesOutput;
import com.example.bff.core.exceptions.NoSuchItemException;
import com.example.bff.core.exceptions.NoSuchUserException;
import com.example.bff.persistence.entities.CartItem;
import com.example.bff.persistence.entities.User;
import com.example.bff.persistence.repositories.CartItemRepository;
import com.example.bff.persistence.repositories.UserRepository;
import com.example.zoostoreproject.api.operations.item.GetItemPropertiesOutput;
import com.example.zoostoreproject.api.operations.item.getCartItemProperties.GetCartItemPropertiesInput;
import com.example.zoostoreproject.api.operations.item.getCartItemProperties.GetCartItemPropertiesOutput;
import com.example.zoostoreproject.restExport.ZooStoreRestClient;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class GetAllItemsFromCartOperationProcessor implements GetAllItemsFromCartOperation {

    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;
    private final ZooStoreRestClient zooStoreRestClient;

    @Override
    public GetAllItemsFromCartOutput process(GetAllItemsFromCartInput input) {

        User user = getUserByEmail();

        List<UUID> itemsID = user.getCart()
                .stream()
                .map(CartItem::getItemId)
                .toList();

        Double totalPrice = user.getCart()
                .stream()
                .mapToDouble(CartItem::getPrice)
                .sum();

        GetCartItemPropertiesOutput cartItemProperties = zooStoreRestClient.getCartItemProperties(mapUUIDsToCartItemPropertiesInput(itemsID));

        List<GetFullItemPropertiesOutput> itemsList = cartItemProperties.getItemsList()
                .stream()
                .map(this::mapItemEntityToGetItemPropertiesOutput)
                .toList();


        return GetAllItemsFromCartOutput.builder()
                .itemsList(itemsList)
                .totalPrice(totalPrice)
                .userFirstName(user.getFirstName())
                .userLastName(user.getLastName())
                .build();

    }

    private GetCartItemPropertiesInput mapUUIDsToCartItemPropertiesInput(List<UUID> itemsId) {
        return GetCartItemPropertiesInput
                .builder()
                .itemsId(itemsId)
                .build();

    }

    private GetFullItemPropertiesOutput mapItemEntityToGetItemPropertiesOutput(GetItemPropertiesOutput item) {
        return GetFullItemPropertiesOutput
                .builder()
                .id(item.getId())
                .productName(item.getProductName())
                .description(item.getDescription())
                .vendorName(item.getVendorName())
                .multimedia(item.getMultimedia())
                .tags(item.getTags())
                .archived(item.isArchived())
                .quantity(getCartItemFromRepository(UUID.fromString(item.getId())).getQuantity())
                .price(getCartItemPrice(UUID.fromString(item.getId())))
                .build();

    }



    private User getUserByEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByEmail(authentication.getName()).
                orElseThrow(NoSuchUserException::new);
    }

    private Double getCartItemPrice(UUID itemId) {

        return getCartItemFromRepository(itemId).getPrice() / getCartItemFromRepository(itemId).getQuantity();
    }


    private CartItem getCartItemFromRepository(UUID itemId){

      return cartItemRepository.findByItemId(itemId)
                .orElseThrow(NoSuchItemException::new);
    }

}
