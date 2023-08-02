package com.example.bff.api.operations.item.getItemsByTagTitle;

import com.example.bff.api.operations.base.OperationResult;
import com.example.bff.api.operations.item.getItemProperties.GetFullItemPropertiesOutput;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetItemsListByTagTitleOutput implements OperationResult {

    private List<GetFullItemPropertiesOutput> itemsList;

}
