package com.testproject.coffeehouseapi.repository;

import com.testproject.coffeehouseapi.dto.OrderFilter;
import com.testproject.coffeehouseapi.model.Order;

import java.util.List;

public interface FilterOrderRepository {

    List<Order> findCoffeeHouseOrdersByFilter(Long coffeeHouseId, OrderFilter orderFilter);
}
