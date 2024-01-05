package com.project.ch.dto;

import com.project.ch.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {

    private Long id;

    private CoffeeHouseDto coffeeHouse;

    private Integer totalCost;

    private LocalDateTime createdAt;

    private LocalDateTime pickUpAt;

    private LocalDateTime closedAt;

    private Status status;

    private List<OrderDetailsDto> orderDetails;
}
