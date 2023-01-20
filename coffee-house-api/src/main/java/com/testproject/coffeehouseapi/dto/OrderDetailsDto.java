package com.testproject.coffeehouseapi.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetailsDto {

    private OrderProductDto product;

    private Integer quantity;

    private Integer cost;

    private Set<OrderProductDto> additives;
}
