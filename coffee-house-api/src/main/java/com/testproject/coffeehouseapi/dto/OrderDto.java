package com.testproject.coffeehouseapi.dto;

import com.testproject.coffeehouseapi.model.Status;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {

    private Long id;

    private CoffeeHouseAddressDto coffeeHouse;

    private Integer totalCost;

    private LocalDateTime createdAt;

    private LocalDateTime pickUpAt;

    private LocalDateTime closedAt;

    private Status status;

    private List<OrderDetailsDto> orderDetails;
}
