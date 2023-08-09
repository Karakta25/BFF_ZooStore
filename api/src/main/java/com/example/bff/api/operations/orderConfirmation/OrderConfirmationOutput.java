package com.example.bff.api.operations.orderConfirmation;

import com.example.zoostoreproject.api.operations.base.OperationResult;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderConfirmationOutput implements OperationResult, com.example.bff.api.operations.base.OperationResult {

    private Double totalPrice;
}
