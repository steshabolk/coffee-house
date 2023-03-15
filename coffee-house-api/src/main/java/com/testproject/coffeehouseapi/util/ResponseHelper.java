package com.testproject.coffeehouseapi.util;

import com.testproject.coffeehouseapi.dto.AvailableMenuDto;
import com.testproject.coffeehouseapi.dto.ProductAvailabilityDto;
import com.testproject.coffeehouseapi.dto.ProductDto;
import com.testproject.coffeehouseapi.dto.response.CoffeeHouseMenuResponse;
import com.testproject.coffeehouseapi.dto.response.OrdersResponse;
import com.testproject.coffeehouseapi.exception.MessageConstant;
import com.testproject.coffeehouseapi.model.CategoryEnum;
import com.testproject.coffeehouseapi.model.CoffeeHouse;
import com.testproject.coffeehouseapi.model.Order;
import com.testproject.coffeehouseapi.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ResponseHelper {

    private final DtoMapper dtoMapper;

    public ResponseHelper(DtoMapper dtoMapper) {
        this.dtoMapper = dtoMapper;
    }

    public OrdersResponse getOrdersResponse(List<Order> orders) {
        return new OrdersResponse(orders.stream()
                .map(dtoMapper::convertToOrderDto)
                .toList());
    }

    public AvailableMenuDto getAvailableMenuDto(CoffeeHouse coffeeHouse, List<Product> products) {
        return new AvailableMenuDto(coffeeHouse.getId(), getProductsMapByCategory(products));
    }

    public CoffeeHouseMenuResponse getCoffeeHouseMenu(CoffeeHouse coffeeHouse, List<Product> products) {
        return new CoffeeHouseMenuResponse(getProductsMapByCategory(products, coffeeHouse));
    }

    private Map<String, List<ProductDto>> getProductsMapByCategory(List<Product> products) {
        Map<String, List<ProductDto>> menuResponse = new LinkedHashMap<>();
        for (CategoryEnum categoryEnum : CategoryEnum.values()) {
            menuResponse.put(toCamelCase(categoryEnum.name(), '_'), getProductsListByCategory(products, categoryEnum));
        }
        return menuResponse;
    }

    private Map<String, List<ProductAvailabilityDto>> getProductsMapByCategory(List<Product> products, CoffeeHouse coffeeHouse) {
        Map<String, List<ProductAvailabilityDto>> menuResponse = new LinkedHashMap<>();
        for (CategoryEnum categoryEnum : CategoryEnum.values()) {
            menuResponse.put(toCamelCase(categoryEnum.name(), '_'), getProductsListByCategory(products, categoryEnum, coffeeHouse));
        }
        return menuResponse;
    }

    private List<ProductDto> getProductsListByCategory(List<Product> productsList, CategoryEnum categoryEnum) {
        return productsList.stream()
                .filter(product -> product.getProductCategory().getCategoryName().equals(categoryEnum))
                .map(dtoMapper::convertToProductDto)
                .collect(Collectors.toList());
    }

    private List<ProductAvailabilityDto> getProductsListByCategory(List<Product> productsList, CategoryEnum categoryEnum, CoffeeHouse coffeeHouse) {
        List<ProductAvailabilityDto> response = productsList.stream()
                .filter(product -> product.getProductCategory().getCategoryName().equals(categoryEnum))
                .map(dtoMapper::convertToProductAvailabilityDto).toList();
        response.forEach(product -> product.setAvailability(coffeeHouse.getAvailableProducts().get(product.getId())));
        return response;
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

    public String currentDateTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(MessageConstant.DATE_TIME_PATTERN));
    }
}
