package com.example.bff.api.operations.item.getItemProperties;

import com.example.bff.api.operations.base.OperationResult;
import com.example.zoostoreproject.api.operations.multimedia.GetMultimediaPropertiesOutput;
import com.example.zoostoreproject.api.operations.tags.GetTagPropertiesOutput;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetFullItemPropertiesOutput implements OperationResult {

    private String id;
    private String productName;
    private String description;
    private String vendorName;
    private boolean archived;
    private Set<GetMultimediaPropertiesOutput> multimedia;
    private Set<GetTagPropertiesOutput> tags;
    private int quantity;
    private double price;
}
