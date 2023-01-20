package com.testproject.coffeehouseapi.controller;

import com.testproject.coffeehouseapi.dto.AllProductsDto;
import com.testproject.coffeehouseapi.dto.response.AllProductsResponse;
import com.testproject.coffeehouseapi.dto.response.CoffeeHouseAddressResponse;
import com.testproject.coffeehouseapi.model.Product;
import com.testproject.coffeehouseapi.service.CoffeeHouseService;
import com.testproject.coffeehouseapi.util.DtoMapper;
import com.testproject.coffeehouseapi.util.ResponseHelper;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "CoffeeHouse", description = "CoffeeHouse API")
@RestController
@RequestMapping("/api/v1/coffeehouse")
public class CoffeeHouseController {

    private final CoffeeHouseService coffeeHouseService;
    private final DtoMapper dtoMapper;
    private final ResponseHelper responseHelper;

    @Autowired
    public CoffeeHouseController(CoffeeHouseService coffeeHouseService, DtoMapper dtoMapper, ResponseHelper responseHelper) {
        this.coffeeHouseService = coffeeHouseService;
        this.dtoMapper = dtoMapper;
        this.responseHelper = responseHelper;
    }

    @GetMapping()
    public ResponseEntity<?> getCoffeeHousesAddresses() {
        return new ResponseEntity<>(
                new CoffeeHouseAddressResponse(
                        coffeeHouseService.findAll().stream()
                                .map(dtoMapper::convertToCoffeeHouseAddressDto)
                                .collect(Collectors.toList())), HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<?> getAllAvailableProducts() {
        List<AllProductsDto> allAvailableProducts = coffeeHouseService.findAllAvailableProducts();
        return new ResponseEntity<>(new AllProductsResponse(allAvailableProducts), HttpStatus.OK);
    }

//    @GetMapping("/{coffeeHouseId}/products")
//    public ResponseEntity<?> getCoffeeHouseAvailableProducts(@PathVariable("coffeeHouseId") Long coffeeHouseId) {
//        List<Product> availableProducts = coffeeHouseService.findCoffeeHouseAvailableProducts(coffeeHouseService.findById(coffeeHouseId));
//        return new ResponseEntity<>(responseHelper.getCoffeeHouseProductsResponse(availableProducts), HttpStatus.OK);
//    }
}
