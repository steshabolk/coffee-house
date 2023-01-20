package com.testproject.coffeehouseapi.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {

    private Long id;

    private String productName;

    private String sizeValue;

    private Double price;

    private String description;

}
