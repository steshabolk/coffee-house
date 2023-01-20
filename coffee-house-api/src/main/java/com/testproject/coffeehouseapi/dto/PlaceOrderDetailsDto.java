package com.testproject.coffeehouseapi.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceOrderDetailsDto {

    @NotNull(message = "{field.notNull}")
    private PlaceOrderProductDto product;

    @NotNull(message = "{field.notNull}")
    @Positive(message = "{positive.number}")
    private Integer quantity;

    @NotNull(message = "{field.notNull}")
    @Positive(message = "{positive.number}")
    private Integer cost;

    @NotNull(message = "{field.notNull}")
    private Set<PlaceOrderProductDto> additives;
}
