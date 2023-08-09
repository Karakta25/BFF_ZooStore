package com.example.bff.rest.contollers;

import com.example.bff.api.operations.cart.addItemToCart.AddItemToCartInput;
import com.example.bff.api.operations.cart.addItemToCart.AddItemToCartOperation;
import com.example.bff.api.operations.cart.addItemToCart.AddItemToCartOutput;
import com.example.bff.api.operations.cart.changeItemQuantity.ChangeItemQuantityInput;
import com.example.bff.api.operations.cart.changeItemQuantity.ChangeItemQuantityOperation;
import com.example.bff.api.operations.cart.changeItemQuantity.ChangeItemQuantityOutput;
import com.example.bff.api.operations.cart.dropCart.DropCartInput;
import com.example.bff.api.operations.cart.dropCart.DropCartOperation;
import com.example.bff.api.operations.cart.dropCart.DropCartOutput;
import com.example.bff.api.operations.cart.getAllItemsFromCart.GetAllItemsFromCartInput;
import com.example.bff.api.operations.cart.getAllItemsFromCart.GetAllItemsFromCartOperation;
import com.example.bff.api.operations.cart.getAllItemsFromCart.GetAllItemsFromCartOutput;
import com.example.bff.api.operations.cart.removeItemFromCart.RemoveItemFromCartInput;
import com.example.bff.api.operations.cart.removeItemFromCart.RemoveItemFromCartOperation;
import com.example.bff.api.operations.cart.removeItemFromCart.RemoveItemFromCartOutput;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/cart")
@RequiredArgsConstructor
public class CartController {

    private final AddItemToCartOperation addItemToCartOperation;
    private final DropCartOperation dropCartOperation;
    private final ChangeItemQuantityOperation changeItemQuantityOperation;
    private final RemoveItemFromCartOperation removeItemFromCartOperation;
    private final GetAllItemsFromCartOperation getAllItemsFromCartOperation;

    @PostMapping
    public ResponseEntity<AddItemToCartOutput> addItemToCart(@RequestBody AddItemToCartInput input) {

        AddItemToCartOutput response = addItemToCartOperation.process(input);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("cartItems")
    public ResponseEntity<GetAllItemsFromCartOutput> getAllItemsFromCart() {

        GetAllItemsFromCartInput input = GetAllItemsFromCartInput.builder().build();
        GetAllItemsFromCartOutput response = getAllItemsFromCartOperation.process(input);
        return ResponseEntity.ok(response);
    }

    @PatchMapping(path = "/quantity")
    public ResponseEntity<ChangeItemQuantityOutput> changeItemQuantity(@Valid @RequestBody ChangeItemQuantityInput input)
    {
        ChangeItemQuantityOutput response = this.changeItemQuantityOperation.process(input);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("item")
    public ResponseEntity<RemoveItemFromCartOutput> removeItemFromCart(@Valid @RequestBody RemoveItemFromCartInput input) {
        RemoveItemFromCartOutput response = this.removeItemFromCartOperation.process(input);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<DropCartOutput> dropCart(@Valid @RequestBody DropCartInput input) {
        DropCartOutput response = this.dropCartOperation.process(input);
        return ResponseEntity.ok(response);
    }
}
