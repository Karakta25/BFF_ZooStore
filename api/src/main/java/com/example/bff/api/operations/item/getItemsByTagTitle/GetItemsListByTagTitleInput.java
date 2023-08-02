package com.example.bff.api.operations.item.getItemsByTagTitle;

import com.example.bff.api.operations.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetItemsListByTagTitleInput implements OperationInput {

    @NotBlank(message = "Title tag must not be blank!")
    private String titleTag;
    @NotNull
    Integer itemCount;
    @NotNull
    Integer page;

}
