package com.example.bff.api.operations.cart.removeItemFromCart;

import com.example.bff.api.operations.base.OperationInput;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RemoveItemFromCartInput implements OperationInput {

    private String itemId;
}
