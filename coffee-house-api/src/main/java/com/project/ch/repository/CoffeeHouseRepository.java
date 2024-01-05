package com.project.ch.repository;

import com.project.ch.entity.CoffeeHouse;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CoffeeHouseRepository extends JpaRepository<CoffeeHouse, Long> {

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = {"manager", "availableProducts"})
    List<CoffeeHouse> findAll();

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = {"manager", "availableProducts"})
    Optional<CoffeeHouse> findWithMenuById(Long id);

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = {"manager", "availableProducts"})
    Optional<CoffeeHouse> findWithMenuByManagerId(Long id);
}
