package com.example.bff.rest.contollers;

import com.example.bff.api.operations.orderConfirmation.OrderConfirmationInput;
import com.example.bff.api.operations.orderConfirmation.OrderConfirmationOperation;
import com.example.bff.api.operations.orderConfirmation.OrderConfirmationOutput;
import com.example.bff.api.operations.payment.PaymentInput;
import com.example.bff.api.operations.payment.PaymentOutput;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderConfirmationOperation orderConfirmationOperation;
    @PostMapping
    public ResponseEntity<OrderConfirmationOutput> orderConfirmation(@RequestBody @Valid OrderConfirmationInput input) {
        OrderConfirmationOutput response = orderConfirmationOperation.process(input);
        return ResponseEntity.ok(response);
    }
}
