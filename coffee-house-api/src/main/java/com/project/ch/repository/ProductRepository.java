package com.project.ch.repository;

import com.project.ch.entity.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = "category")
    List<Product> findByIdIn(Collection<Long> id);

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = "category")
    List<Product> findAll();
}
