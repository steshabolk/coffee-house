package com.project.ch.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
