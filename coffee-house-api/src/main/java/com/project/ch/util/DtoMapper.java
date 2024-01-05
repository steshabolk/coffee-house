package com.project.ch.util;

import com.project.ch.dto.CoffeeHouseDto;
import com.project.ch.dto.OrderDto;
import com.project.ch.dto.PlaceOrderDetailsDto;
import com.project.ch.dto.PlaceOrderDto;
import com.project.ch.dto.ProductAvailabilityDto;
import com.project.ch.dto.ProductDto;
import com.project.ch.entity.CoffeeHouse;
import com.project.ch.entity.Order;
import com.project.ch.entity.OrderDetails;
import com.project.ch.entity.Product;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DtoMapper {

    private final ModelMapper modelMapper;

    public DtoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Order convertToOrder(PlaceOrderDto placeOrderDto) {
        return modelMapper.map(placeOrderDto, Order.class);
    }

    public OrderDetails convertToOrderDetails(PlaceOrderDetailsDto placeOrderDetailsDto) {
        return modelMapper.map(placeOrderDetailsDto, OrderDetails.class);
    }

    public CoffeeHouseDto convertToCHDto(CoffeeHouse coffeeHouse) {
        return modelMapper.map(coffeeHouse, CoffeeHouseDto.class);
    }

    public ProductDto convertToProductDto(Product product) {
        return modelMapper.map(product, ProductDto.class);
    }

    public ProductAvailabilityDto convertToProductAvailabilityDto(Product product) {
        return modelMapper.map(product, ProductAvailabilityDto.class);
    }

    public OrderDto convertToOrderDto(Order order) {
        return modelMapper.map(order, OrderDto.class);
    }

}
