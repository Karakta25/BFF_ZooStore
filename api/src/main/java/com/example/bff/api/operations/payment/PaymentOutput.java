package com.example.bff.api.operations.payment;

import com.example.bff.api.operations.base.OperationResult;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentOutput implements OperationResult {

    private String userId;
    private boolean success;

}
