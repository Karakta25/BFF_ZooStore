package com.example.bff.core.operations;

import com.example.bff.api.operations.item.getItemsByTagTitle.GetItemsListByTagTitleInput;
import com.example.bff.api.operations.item.getItemsByTagTitle.GetItemsListByTagTitleOperation;
import com.example.bff.api.operations.item.getItemsByTagTitle.GetItemsListByTagTitleOutput;
import com.example.bff.api.operations.item.getItemProperties.GetFullItemPropertiesOutput;
import com.example.zoostoreproject.api.operations.item.GetItemPropertiesOutput;
import com.example.zoostoreproject.api.operations.item.getItemByTitleTag.GetItemByTitleTagOutput;
import com.example.zoostoreproject.restExport.ZooStoreRestClient;
import com.example.zoostorestorage.api.operations.itemStorage.getItemById.GetItemStorageByIdOutput;
import com.example.zoostorestorage.restExport.ZooStorageRestClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class GetItemsListByTagTitleOperationProcessor implements GetItemsListByTagTitleOperation {

    private final ZooStorageRestClient zooStorageRestClient;
    private final ZooStoreRestClient zooStoreRestClient;

    @Override
    public GetItemsListByTagTitleOutput process(GetItemsListByTagTitleInput input) {

       /*try{
           zooStoreRestClient.getItemByTitleTag(input.getTitleTag(),input.getItemCount(),input.getPage());
       }
       catch (Exception e)
       {
           throw new NoSuchTagException();
       }*/

        GetItemsListByTagTitleOutput itemsOutput = GetItemsListByTagTitleOutput
                .builder()
                .itemsList(new ArrayList<>())
                .build();


        GetItemByTitleTagOutput itemByTitleTag = zooStoreRestClient.getItemByTitleTag(input.getTitleTag(), input.getItemCount(), input.getPage());


        for (GetItemPropertiesOutput item : itemByTitleTag.getItemsList()) {

            GetItemStorageByIdOutput storageItem = zooStorageRestClient.getItemFromStorage(item.getId());

            GetFullItemPropertiesOutput BFFItem = GetFullItemPropertiesOutput
                    .builder()
                    .id(item.getId())
                    .productName(item.getProductName())
                    .description(item.getDescription())
                    .vendorId(item.getVendorId())
                    .multimedia(item.getMultimedia())
                    .tags(item.getTags())
                    .archived(item.isArchived())
                    .quantity(storageItem.getQuantity())
                    .price(storageItem.getPrice())
                    .build();


            itemsOutput.getItemsList().add(BFFItem);
        }
        return itemsOutput;
    }

}


