package com.testproject.coffeehouseapi.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.testproject.coffeehouseapi.dto.OrderFilter;
import com.testproject.coffeehouseapi.model.Order;
import com.testproject.coffeehouseapi.model.QOrder;
import com.testproject.coffeehouseapi.util.QPredicates;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
public class FilterOrderRepositoryImpl implements FilterOrderRepository {

    private final EntityManager entityManager;

    @Override
    public List<Order> findCoffeeHouseOrdersByFilter(Long coffeeHouseId, OrderFilter orderFilter) {

        QOrder order = QOrder.order;

        Predicate predicate = QPredicates.builder()
                .add(orderFilter.getId(), order.id::eq)
                .add(orderFilter.getCreatedAt_from(), order.createdAt::after)
                .add(orderFilter.getCreatedAt_to(), order.createdAt::before)
                .add(orderFilter.getPickUpAt_from(), order.pickUpAt::after)
                .add(orderFilter.getPickUpAt_to(), order.pickUpAt::before)
                .add(orderFilter.getClosedAt_from(), order.closedAt::after)
                .add(orderFilter.getClosedAt_to(), order.closedAt::before)
                .add(orderFilter.getStatus(), order.status::in)
                .build();

        JPAQuery<?> jpaQuery = new JPAQuery<>(entityManager);
        jpaQuery.setHint("javax.persistence.fetchgraph", entityManager.getEntityGraph("graph.Order.orderDetails"));

        return jpaQuery
                .select(order)
                .from(order)
                .where(order.coffeeHouse.id.eq(coffeeHouseId)
                        .and(predicate))
                .orderBy(order.createdAt.desc())
                .fetch();
    }
}
