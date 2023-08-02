package com.example.bff.rest.contollers;

import com.example.bff.api.operations.item.getItemProperties.GetFullItemPropertiesInput;
import com.example.bff.api.operations.item.getItemProperties.GetFullItemPropertiesOperation;
import com.example.bff.api.operations.item.getItemProperties.GetFullItemPropertiesOutput;
import com.example.bff.api.operations.item.getItemsByTagTitle.GetItemsListByTagTitleInput;
import com.example.bff.api.operations.item.getItemsByTagTitle.GetItemsListByTagTitleOperation;
import com.example.bff.api.operations.item.getItemsByTagTitle.GetItemsListByTagTitleOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/bff")
@RequiredArgsConstructor
public class BFFController {

    private final GetFullItemPropertiesOperation getFullItemPropertiesOperation;
    private final GetItemsListByTagTitleOperation getItemsListByTagTitleOperation;

    @GetMapping(path = "/itemById/{itemId}")
    public ResponseEntity<GetFullItemPropertiesOutput> getItemProperties(@PathVariable String itemId)
    {
        GetFullItemPropertiesInput input = GetFullItemPropertiesInput.builder().itemId(itemId).build();

        GetFullItemPropertiesOutput response = this.getFullItemPropertiesOperation.process(input);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/itemsByTagTitle")
    public ResponseEntity<GetItemsListByTagTitleOutput> getItemsListByTagTitle(
            @RequestParam(name = "title") String title,
            @RequestParam(name = "itemsPerPage") Integer itemsPerPage,
            @RequestParam(name = "currentPage") Integer currentPage)
    {
        GetItemsListByTagTitleInput input = GetItemsListByTagTitleInput.builder()
                .titleTag(title)
                .itemCount(itemsPerPage)
                .page(currentPage)
                .build();

        GetItemsListByTagTitleOutput response = this.getItemsListByTagTitleOperation.process(input);
        return ResponseEntity.ok(response);
    }
}
