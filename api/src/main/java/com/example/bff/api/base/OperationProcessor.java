package com.example.bff.api.base;

public interface OperationProcessor<I extends OperationInput, R extends OperationResult>{

    R process(I input);
}
