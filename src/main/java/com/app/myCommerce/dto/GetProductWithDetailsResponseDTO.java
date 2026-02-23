package com.app.myCommerce.dto;

import com.app.myCommerce.schema.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class GetProductWithDetailsResponseDTO extends GetProductResponseDTO {

    private Category category;

}
