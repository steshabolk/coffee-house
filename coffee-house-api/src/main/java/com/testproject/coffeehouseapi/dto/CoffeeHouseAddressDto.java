package com.testproject.coffeehouseapi.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoffeeHouseAddressDto {

    private Long id;

    private String city;

    private String address;
}
