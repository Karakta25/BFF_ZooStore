package com.example.bff.core.operations;

import com.example.bff.api.operations.item.getItemProperties.GetFullItemPropertiesInput;
import com.example.bff.api.operations.item.getItemProperties.GetFullItemPropertiesOperation;
import com.example.bff.api.operations.item.getItemProperties.GetFullItemPropertiesOutput;
import com.example.bff.core.exceptions.NoSuchItemException;
import com.example.zoostoreproject.api.operations.item.getItemById.GetItemByIdOutput;
import com.example.zoostoreproject.restExport.ZooStoreRestClient;
import com.example.zoostorestorage.api.operations.itemStorage.getItemById.GetItemStorageByIdOutput;
import com.example.zoostorestorage.restExport.ZooStorageRestClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class GetFullItemPropertiesOperationProcessor implements GetFullItemPropertiesOperation {

    private final ZooStoreRestClient zooStoreRestClient;
    private final ZooStorageRestClient zooStorageRestClient;

    @Override
    public GetFullItemPropertiesOutput process(GetFullItemPropertiesInput input) {

       try {
           zooStoreRestClient.getItemById(input.getItemId());}
       catch(Exception e) {
           throw new NoSuchItemException();}

       try {
           zooStoreRestClient.getItemById(input.getItemId());}
       catch(Exception e) {
           throw new NoSuchItemException();}


        GetItemByIdOutput item = zooStoreRestClient.getItemById(input.getItemId());
        GetItemStorageByIdOutput storageItem = zooStorageRestClient.getItemFromStorage(input.getItemId());



        return GetFullItemPropertiesOutput.builder()
                .id(item.getId())
                .productName(item.getProductName())
                .description(item.getDescription())
                .vendorId(item.getVendorId())
                .archived(item.isArchived())
                .multimedia(item.getMultimedia())
                .tags(item.getTags())
                .quantity(storageItem.getQuantity())
                .price(storageItem.getPrice())
                .build();

    }

}
