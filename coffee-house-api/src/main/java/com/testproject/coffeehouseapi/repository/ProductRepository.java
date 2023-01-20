package com.testproject.coffeehouseapi.repository;

import com.testproject.coffeehouseapi.model.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = "productCategory")
    @Query("select p from Product p where p.id in :ids order by p.id")
    List<Product> findByProductIds(@Param("ids") Iterable<Long> ids);

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = "productCategory")
    List<Product> findAll();
}
