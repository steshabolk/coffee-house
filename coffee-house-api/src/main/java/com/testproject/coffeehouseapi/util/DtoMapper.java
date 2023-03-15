package com.testproject.coffeehouseapi.util;

import com.testproject.coffeehouseapi.dto.*;
import com.testproject.coffeehouseapi.dto.request.RegisterRequest;
import com.testproject.coffeehouseapi.model.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DtoMapper {

    private final ModelMapper modelMapper;

    public DtoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public User convertToUser(RegisterRequest registerRequest) {
        return modelMapper.map(registerRequest, User.class);
    }

    public Order convertToOrder(PlaceOrderDto placeOrderDto) {
        return modelMapper.map(placeOrderDto, Order.class);
    }
    public OrderDetails convertToOrderDetails(PlaceOrderDetailsDto placeOrderDetailsDto) {
        return modelMapper.map(placeOrderDetailsDto, OrderDetails.class);
    }

    public CoffeeHouseAddressDto convertToCoffeeHouseAddressDto(CoffeeHouse coffeeHouse) {
        return modelMapper.map(coffeeHouse, CoffeeHouseAddressDto.class);
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
