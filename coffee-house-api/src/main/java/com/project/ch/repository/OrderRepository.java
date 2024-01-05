package com.project.ch.repository;

import com.project.ch.entity.Order;
import com.project.ch.entity.Status;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, CustomRepository<Order, Long>, JpaSpecificationExecutor<Order> {

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {"user", "coffeeHouse", "coffeeHouse.manager", "status"})
    Optional<Order> findById(Long id);

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {"user", "coffeeHouse", "coffeeHouse.manager", "orderDetails"})
    List<Order> findByUserIdOrderByCreatedAt(Long id);

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {"user", "coffeeHouse", "coffeeHouse.manager", "orderDetails"})
    List<Order> findByCoffeeHouseIdAndStatusOrderByPickUpAt(Long id, Status status);

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {"user", "coffeeHouse", "coffeeHouse.manager", "orderDetails"})
    List<Order> findAll(Specification<Order> spec);
}
