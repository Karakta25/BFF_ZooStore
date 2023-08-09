package com.example.bff.api.operations.cart.dropCart;

import com.example.bff.api.operations.base.OperationProcessor;
import com.example.bff.api.operations.cart.changeItemQuantity.ChangeItemQuantityInput;
import com.example.bff.api.operations.cart.changeItemQuantity.ChangeItemQuantityOutput;

public interface DropCartOperation extends OperationProcessor<DropCartInput, DropCartOutput> {
}
