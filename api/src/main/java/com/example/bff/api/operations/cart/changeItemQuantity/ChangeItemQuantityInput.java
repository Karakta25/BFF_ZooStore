package com.example.bff.api.operations.cart.changeItemQuantity;

import com.example.bff.api.operations.base.OperationInput;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChangeItemQuantityInput implements OperationInput {

    private String itemId;
    private int quantity;
}
