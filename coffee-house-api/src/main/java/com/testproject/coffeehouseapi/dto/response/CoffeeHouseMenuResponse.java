package com.testproject.coffeehouseapi.dto.response;

import com.testproject.coffeehouseapi.dto.ProductAvailabilityDto;
import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoffeeHouseMenuResponse {

    private Map<String, List<ProductAvailabilityDto>> menu;
}
