package com.testproject.coffeehouseapi.repository;

import com.testproject.coffeehouseapi.model.OrderDetails;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {"product", "additives"})
    @Query("select od from OrderDetails od where od.id in :ids")
    List<OrderDetails> findByOrderDetailsIds(@Param("ids") Iterable<Long> ids);

}
