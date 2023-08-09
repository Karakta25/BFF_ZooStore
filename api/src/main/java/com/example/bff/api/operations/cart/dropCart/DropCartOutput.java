package com.example.bff.api.operations.cart.dropCart;

import com.example.bff.api.operations.base.OperationResult;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DropCartOutput implements OperationResult {

    private boolean success;
}
