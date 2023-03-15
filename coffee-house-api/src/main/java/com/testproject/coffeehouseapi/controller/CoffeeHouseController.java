package com.testproject.coffeehouseapi.controller;

import com.testproject.coffeehouseapi.dto.response.AvailableMenuResponse;
import com.testproject.coffeehouseapi.dto.response.CoffeeHouseAddressResponse;
import com.testproject.coffeehouseapi.service.CoffeeHouseService;
import com.testproject.coffeehouseapi.util.DtoMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@Tag(name = "CoffeeHouse", description = "CoffeeHouse API")
@RestController
@RequestMapping("/api/v1/coffeehouse")
public class CoffeeHouseController {

    private final CoffeeHouseService coffeeHouseService;
    private final DtoMapper dtoMapper;

    @Autowired
    public CoffeeHouseController(CoffeeHouseService coffeeHouseService, DtoMapper dtoMapper) {
        this.coffeeHouseService = coffeeHouseService;
        this.dtoMapper = dtoMapper;
    }

    @GetMapping()
    public ResponseEntity<?> getCoffeeHousesAddresses() {
        return new ResponseEntity<>(
                new CoffeeHouseAddressResponse(
                        coffeeHouseService.findAll().stream()
                                .map(dtoMapper::convertToCoffeeHouseAddressDto)
                                .collect(Collectors.toList())), HttpStatus.OK);
    }

    @GetMapping("/menu")
    public ResponseEntity<?> getAvailableMenu() {
        return new ResponseEntity<>(new AvailableMenuResponse(coffeeHouseService.findAvailableMenu()), HttpStatus.OK);
    }
}
