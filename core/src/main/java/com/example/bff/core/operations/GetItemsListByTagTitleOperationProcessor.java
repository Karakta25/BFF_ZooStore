package com.example.bff.core.operations;

import com.example.bff.api.operations.item.getItemsByTagTitle.GetItemsListByTagTitleInput;
import com.example.bff.api.operations.item.getItemsByTagTitle.GetItemsListByTagTitleOperation;
import com.example.bff.api.operations.item.getItemsByTagTitle.GetItemsListByTagTitleOutput;
import com.example.bff.api.operations.item.getItemProperties.GetFullItemPropertiesOutput;
import com.example.zoostoreproject.api.operations.item.GetItemPropertiesOutput;
import com.example.zoostoreproject.api.operations.item.getItemByTitleTag.GetItemByTitleTagOutput;
import com.example.zoostoreproject.persistence.entities.Item;
import com.example.zoostoreproject.restExport.ZooStoreRestClient;
import com.example.zoostorestorage.api.operations.itemStorage.getItemById.GetItemStorageByIdOutput;
import com.example.zoostorestorage.restExport.ZooStorageRestClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

        GetItemByTitleTagOutput itemByTitleTag = zooStoreRestClient.getItemByTitleTag(input.getTitleTag(), input.getItemCount(), input.getPage());

        List<GetFullItemPropertiesOutput> items = itemByTitleTag.getItemsList()
                .stream()
                .map(item -> {
                    GetItemStorageByIdOutput storageItem = zooStorageRestClient.getItemFromStorage(item.getId());
                    return getFullItemProperties(item, storageItem);})
                .collect(Collectors.toList());

        return  GetItemsListByTagTitleOutput
                .builder()
                .itemsList(items)
                .build();
    }

    private GetFullItemPropertiesOutput getFullItemProperties(GetItemPropertiesOutput item, GetItemStorageByIdOutput storageItem) {
            return GetFullItemPropertiesOutput.builder()
                .id(item.getId())
                .productName(item.getProductName())
                .description(item.getDescription())
                .vendorName(item.getVendorName())
                .multimedia(item.getMultimedia())
                .tags(item.getTags())
                .archived(item.isArchived())
                .quantity(storageItem.getQuantity())
                .price(storageItem.getPrice())
                .build();
    }
}


