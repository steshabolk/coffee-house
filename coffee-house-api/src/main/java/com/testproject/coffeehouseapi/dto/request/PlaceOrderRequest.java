package com.testproject.coffeehouseapi.dto.request;

import com.testproject.coffeehouseapi.dto.PlaceOrderDetailsDto;
import com.testproject.coffeehouseapi.dto.PlaceOrderDto;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PlaceOrderRequest {

    @NotNull(message = "{field.notNull}")
    private PlaceOrderDto order;

    @NotNull(message = "{field.notNull}")
    private List<PlaceOrderDetailsDto> orderDetails;
}
