package com.example.bff.api.operations.cart.addItemToCart;

import com.example.bff.api.operations.base.OperationInput;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddItemToCartInput implements OperationInput {

    private String itemId;
    @NotNull
    private int quantity;


}
