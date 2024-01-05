package com.project.ch.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
