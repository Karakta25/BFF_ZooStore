package com.example.bff.core.operations;

import com.example.bff.api.operations.user.changeUserProperties.ChangeUserPropertiesInput;
import com.example.bff.api.operations.user.changeUserProperties.ChangeUserPropertiesOperation;
import com.example.bff.api.operations.user.changeUserProperties.ChangeUserPropertiesOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChangeUserPropertiesOperationProcessor implements ChangeUserPropertiesOperation {
    @Override
    public ChangeUserPropertiesOutput process(ChangeUserPropertiesInput input) {
        return null;
    }
}
