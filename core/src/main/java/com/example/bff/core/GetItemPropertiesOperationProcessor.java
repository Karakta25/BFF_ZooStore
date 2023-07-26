package com.example.bff.core;

import com.example.bff.api.getItemProperties.GetItemPropertiesInput;
import com.example.bff.api.getItemProperties.GetItemPropertiesOperation;
import com.example.bff.api.getItemProperties.GetItemPropertiesOutput;
import com.example.bff.core.exceptions.NoSuchItemException;
import com.example.zoostoreproject.api.operations.item.getItemById.GetItemByIdOutput;
import com.example.zoostoreproject.restExport.ZooStoreRestClient;
import com.example.zoostorestorage.api.operations.itemStorage.getItemById.GetItemStorageByIdOutput;
import com.example.zoostorestorage.restExport.ZooStorageRestClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class GetItemPropertiesOperationProcessor implements GetItemPropertiesOperation {

    private final ZooStoreRestClient zooStoreRestClient;
    private final ZooStorageRestClient zooStorageRestClient;

    @Override
    public GetItemPropertiesOutput process(GetItemPropertiesInput input) {

//        try {
//            zooStoreRestClient.getItemById(input.getItemId());}
//        catch(Exception e) {
//            throw new NoSuchItemException();}
//
//       try {
//            zooStoreRestClient.getItemById(input.getItemId());}
//        catch(Exception e) {
//            throw new NoSuchItemException();}


        GetItemByIdOutput item = zooStoreRestClient.getItemById(input.getItemId());
        GetItemStorageByIdOutput storageItem = zooStorageRestClient.getItemFromStorage(input.getItemId());


        return GetItemPropertiesOutput.builder()
                .id(item.getId())
                .productName(item.getProductName())
                .description(item.getDescription())
                .vendorId(item.getVendorId())
                .archived(item.isArchived())
                .quantity(storageItem.getQuantity())
                .price(storageItem.getPrice())
                .build();

    }

}
