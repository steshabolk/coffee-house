package com.testproject.coffeehouseapi.dto;

import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AllProductsDto {

    private Long id;
    private Map<String, List<ProductDto>> products;
}
