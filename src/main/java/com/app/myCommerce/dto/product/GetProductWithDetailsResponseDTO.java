package com.app.myCommerce.dto.product;

import com.app.myCommerce.schema.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class GetProductWithDetailsResponseDTO extends GetProductResponseDTO {

    private Category category;

}
