package com.testproject.coffeehouseapi.repository;

import com.testproject.coffeehouseapi.model.Order;
import com.testproject.coffeehouseapi.model.Status;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, CustomRepository<Order, Long>,
        FilterOrderRepository, QuerydslPredicateExecutor<Order> {

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {"user", "coffeeHouse", "coffeeHouse.manager", "status"})
    Optional<Order> findById(Long id);

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {"coffeeHouse", "coffeeHouse.manager",
                    "orderDetails", "orderDetails.product", "orderDetails.product.productCategory"})
    List<Order> findByUserIdOrderByCreatedAt(Long id);

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {"coffeeHouse", "coffeeHouse.manager",
                    "orderDetails", "orderDetails.product", "orderDetails.product.productCategory"})
    List<Order> findByCoffeeHouseIdAndStatusOrderByPickUpAt(Long id, Status status);
}
