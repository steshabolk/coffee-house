package com.project.ch.util;


import com.project.ch.dto.AvailableMenuDto;
import com.project.ch.dto.ProductAvailabilityDto;
import com.project.ch.dto.ProductDto;
import com.project.ch.entity.CoffeeHouse;
import com.project.ch.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class Helper {

    private final DtoMapper dtoMapper;

    public Helper(DtoMapper dtoMapper) {
        this.dtoMapper = dtoMapper;
    }

    public Set<Long> findDifference(Set<Long> current, Set<Long> all) {
        return current.stream()
                .filter(id -> !all.contains(id))
                .collect(Collectors.toSet());
    }
    public AvailableMenuDto getAvailableMenuDto(List<Product> products, CoffeeHouse coffeeHouse) {
        return new AvailableMenuDto(coffeeHouse.getId(), groupByCategory(products));
    }

    public Map<String, List<ProductAvailabilityDto>> getCHMenuResponse(List<Product> products, Map<Long, Boolean> availableProducts) {
        return groupByCategory(products, availableProducts);
    }

    private Map<String, List<ProductDto>> groupByCategory(List<Product> products) {
        Map<String, List<Product>> grouped = products.stream().collect(
                Collectors.groupingBy(product -> product.getCategory().getCategoryName().name()));
        return grouped.entrySet().stream().collect(Collectors.toMap(
                e -> toCamelCase(e.getKey(), '_'),
                e -> e.getValue().stream().map(dtoMapper::convertToProductDto).toList()));
    }

    private Map<String, List<ProductAvailabilityDto>> groupByCategory(List<Product> products, Map<Long, Boolean> availableProducts) {
        Map<String, List<Product>> grouped = products.stream().collect(
                Collectors.groupingBy(product -> product.getCategory().getCategoryName().name()));
        return grouped.entrySet().stream().collect(Collectors.toMap(
                e -> toCamelCase(e.getKey(), '_'),
                e -> {
                    List<ProductAvailabilityDto> dtos = e.getValue().stream().map(dtoMapper::convertToProductAvailabilityDto).toList();
                    dtos.forEach(dto -> dto.setAvailability(availableProducts.get(dto.getId())));
                    return dtos;
                }));
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
