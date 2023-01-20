package com.testproject.coffeehouseapi.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoffeeHouseIdDto {

    @NotNull(message = "{field.notNull}")
    private Long id;
}
