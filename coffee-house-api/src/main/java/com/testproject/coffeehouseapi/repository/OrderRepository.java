package com.testproject.coffeehouseapi.repository;

import com.testproject.coffeehouseapi.model.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {"coffeeHouse", "coffeeHouse.manager",
                    "orderDetails", "orderDetails.product", "orderDetails.product.productCategory"})
    List<Order> findAllByUserIdOrderByCreatedAt(Long id);

}
