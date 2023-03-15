package com.testproject.coffeehouseapi.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductAvailabilityDto {

    private Long id;

    private String productName;

    private String sizeValue;

    private Double price;

    private Boolean availability;

}
