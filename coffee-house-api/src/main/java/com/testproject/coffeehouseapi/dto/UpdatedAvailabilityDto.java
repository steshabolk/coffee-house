package com.testproject.coffeehouseapi.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdatedAvailabilityDto {

    @NotNull(message = "{field.notNull}")
    private Long id;

    @NotNull(message = "{field.notNull}")
    private Boolean availability;
}
