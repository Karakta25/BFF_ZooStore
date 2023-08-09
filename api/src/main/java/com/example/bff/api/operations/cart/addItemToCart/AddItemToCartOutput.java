package com.example.bff.api.operations.cart.addItemToCart;

import com.example.bff.api.operations.base.OperationResult;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddItemToCartOutput implements OperationResult {

    private String itemId;
    private Double totalPrice;  //ItemPrice*quantity
    private Integer quantity;

}
