package com.project.ch.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
public class PlaceOrderDetailsDto {

    @NotNull
    private IdDto product;

    @NotNull
    @Positive
    private Integer quantity;

    @NotNull
    @Positive
    private Integer cost;

    @NotNull
    private Set<IdDto> additives;
}
