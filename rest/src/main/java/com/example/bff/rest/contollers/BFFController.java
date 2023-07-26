package com.example.bff.rest.contollers;

import com.example.bff.api.getItemProperties.GetItemPropertiesInput;
import com.example.bff.api.getItemProperties.GetItemPropertiesOperation;
import com.example.bff.api.getItemProperties.GetItemPropertiesOutput;
import com.example.zoostorestorage.api.operations.itemStorage.getItemById.GetItemByIdInput;
import com.example.zoostorestorage.api.operations.itemStorage.getItemById.GetItemStorageByIdOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/bff")
@RequiredArgsConstructor
public class BFFController {

    private final GetItemPropertiesOperation getItemPropertiesOperation;

    @GetMapping(path = "/itemById/{itemId}")
    public ResponseEntity<GetItemPropertiesOutput> getItemProperties(@PathVariable String itemId)
    {
        GetItemPropertiesInput input = GetItemPropertiesInput.builder().itemId(itemId).build();

        GetItemPropertiesOutput response = this.getItemPropertiesOperation.process(input);
        return ResponseEntity.ok(response);
    }
}
