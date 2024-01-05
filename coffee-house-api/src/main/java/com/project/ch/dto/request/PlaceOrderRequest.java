package com.project.ch.dto.request;

import com.project.ch.dto.PlaceOrderDetailsDto;
import com.project.ch.dto.PlaceOrderDto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PlaceOrderRequest {

    @NotNull
    private PlaceOrderDto order;

    @NotNull
    @NotEmpty
    private List<PlaceOrderDetailsDto> orderDetails;
}
