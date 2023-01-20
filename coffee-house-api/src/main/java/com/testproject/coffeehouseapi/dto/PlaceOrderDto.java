package com.testproject.coffeehouseapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.testproject.coffeehouseapi.model.Status;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceOrderDto {

    @NotNull(message = "{field.notNull}")
    private CoffeeHouseIdDto coffeeHouse;

    @NotNull(message = "{field.notNull}")
    @Positive(message = "{positive.number}")
    private Integer totalCost;

    @NotNull(message = "{field.notNull}")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @NotNull(message = "{field.notNull}")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime pickUpAt;
}
