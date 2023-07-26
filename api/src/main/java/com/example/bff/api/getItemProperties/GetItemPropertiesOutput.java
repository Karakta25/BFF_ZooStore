package com.example.bff.api.getItemProperties;

import com.example.bff.api.base.OperationResult;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetItemPropertiesOutput implements OperationResult {

    private String id;
    private String productName;
    private String description;
    private String vendorId;
    private boolean archived;
    private int quantity;
    private double price;
}
