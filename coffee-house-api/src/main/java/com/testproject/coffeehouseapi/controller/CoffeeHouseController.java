package com.testproject.coffeehouseapi.controller;

import com.testproject.coffeehouseapi.dto.response.AvailableMenuResponse;
import com.testproject.coffeehouseapi.dto.response.CoffeeHouseAddressResponse;
import com.testproject.coffeehouseapi.service.CoffeeHouseService;
import com.testproject.coffeehouseapi.util.DtoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get a addresses list of coffee houses", description = "Received data : coffee house id, city, address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The list of addresses of coffee houses is received successfully",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CoffeeHouseAddressResponse.class))))
    })
    @GetMapping()
    public ResponseEntity<?> getCoffeeHousesAddresses() {
        return new ResponseEntity<>(
                new CoffeeHouseAddressResponse(
                        coffeeHouseService.findAll().stream()
                                .map(dtoMapper::convertToCoffeeHouseAddressDto)
                                .collect(Collectors.toList())), HttpStatus.OK);
    }

    @Operation(summary = "Get a menu available for ordering at each coffee house",
            description = "You get a list of available products divided into categories for each coffee house id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The list of available menu products is received successfully",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = AvailableMenuResponse.class))))
    })
    @GetMapping("/menu")
    public ResponseEntity<?> getAvailableMenu() {
        return new ResponseEntity<>(new AvailableMenuResponse(coffeeHouseService.findAvailableMenu()), HttpStatus.OK);
    }
}
