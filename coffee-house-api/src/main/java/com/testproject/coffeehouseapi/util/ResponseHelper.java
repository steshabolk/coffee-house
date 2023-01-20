package com.testproject.coffeehouseapi.util;

import com.testproject.coffeehouseapi.dto.AllProductsDto;
import com.testproject.coffeehouseapi.dto.ProductDto;
import com.testproject.coffeehouseapi.dto.response.UserOrdersResponse;
import com.testproject.coffeehouseapi.model.CategoryEnum;
import com.testproject.coffeehouseapi.model.CoffeeHouse;
import com.testproject.coffeehouseapi.model.Order;
import com.testproject.coffeehouseapi.model.Product;
import com.testproject.coffeehouseapi.service.OrderService;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ResponseHelper {

    private final DtoMapper dtoMapper;

    public ResponseHelper(DtoMapper dtoMapper) {
        this.dtoMapper = dtoMapper;
    }

    public UserOrdersResponse getUserOrdersResponse(List<Order> orders) {
        return new UserOrdersResponse(orders.stream()
                .map(dtoMapper::convertToOrderDto)
                .toList());
    }

    public AllProductsDto getAllProductsDto(CoffeeHouse coffeeHouse, List<Product> products) {
        return new AllProductsDto(coffeeHouse.getId(), getProductsMapByCategory(products));
    }

//    public CoffeeHouseProductsResponse getCoffeeHouseProductsResponse(List<Product> products) {
//        Map<String, List<ProductDto>> productsResponse = getProductsMapByCategory(products);
//        return new CoffeeHouseProductsResponse(productsResponse);
//    }

    private Map<String, List<ProductDto>> getProductsMapByCategory(List<Product> products) {
        Map<String, List<ProductDto>> productsResponse = new LinkedHashMap<>();
        for (CategoryEnum categoryEnum : CategoryEnum.values()) {
            productsResponse.put(toCamelCase(categoryEnum.name(), '_'), getListByCategory(products, categoryEnum));
        }
        return productsResponse;
    }

    private List<ProductDto> getListByCategory(List<Product> productsList, CategoryEnum categoryEnum) {
        return productsList.stream()
                .filter(product -> product.getProductCategory().getCategoryName().equals(categoryEnum))
                .map(dtoMapper::convertToProductDto)
                .collect(Collectors.toList());
    }

    private String toCamelCase(String origin, char delimiter) {
        StringBuilder builder = new StringBuilder();
        boolean shouldConvertNextCharToLower = true;
        for (int i = 0; i < origin.length(); i++) {
            char currentChar = origin.charAt(i);
            if (currentChar == delimiter) {
                shouldConvertNextCharToLower = false;
            } else if (shouldConvertNextCharToLower) {
                builder.append(Character.toLowerCase(currentChar));
            } else {
                builder.append(Character.toUpperCase(currentChar));
                shouldConvertNextCharToLower = true;
            }
        }
        return builder.toString();
    }
}
