package com.testproject.coffeehouseapi.dto.response;

import com.testproject.coffeehouseapi.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class CoffeeHouseProductsResponse {

    private Map<String, List<ProductDto>> products;

}
