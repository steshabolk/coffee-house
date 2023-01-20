package com.testproject.coffeehouseapi.repository;

import com.testproject.coffeehouseapi.model.CoffeeHouse;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoffeeHouseRepository extends JpaRepository<CoffeeHouse, Long> {

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = "manager")
    List<CoffeeHouse> findAll();

}
