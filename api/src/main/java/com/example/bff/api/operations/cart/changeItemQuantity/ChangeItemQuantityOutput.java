package com.example.bff.api.operations.cart.changeItemQuantity;

import com.example.bff.api.operations.base.OperationResult;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChangeItemQuantityOutput implements OperationResult {
    private String itemId;
    private int quantity;
}
