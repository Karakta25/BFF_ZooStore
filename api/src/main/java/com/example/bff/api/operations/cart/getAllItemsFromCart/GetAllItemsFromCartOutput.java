package com.example.bff.api.operations.cart.getAllItemsFromCart;

import com.example.bff.api.operations.base.OperationResult;
import com.example.bff.api.operations.item.getItemProperties.GetFullItemPropertiesOutput;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetAllItemsFromCartOutput implements OperationResult {

    private List<GetFullItemPropertiesOutput> itemsList;
    private Double totalPrice;
    private String userFirstName;
    private String userLastName;
}
