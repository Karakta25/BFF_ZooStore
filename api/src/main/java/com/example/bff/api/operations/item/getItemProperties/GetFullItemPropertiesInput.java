package com.example.bff.api.operations.item.getItemProperties;

import com.example.bff.api.operations.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetFullItemPropertiesInput implements OperationInput {

    @NotBlank(message = "Item id must not be blank!")
    private String itemId;
}
