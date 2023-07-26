package com.example.bff.api.getItemProperties;

import com.example.bff.api.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetItemPropertiesInput implements OperationInput {

    @NotBlank(message = "Item id must not be blank!")
    private String itemId;
}
