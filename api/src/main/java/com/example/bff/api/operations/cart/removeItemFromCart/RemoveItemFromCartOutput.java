package com.example.bff.api.operations.cart.removeItemFromCart;

import com.example.bff.api.operations.base.OperationResult;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RemoveItemFromCartOutput implements OperationResult {

    private boolean success;
}
