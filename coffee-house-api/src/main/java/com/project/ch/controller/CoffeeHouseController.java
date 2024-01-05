package com.project.ch.controller;

import com.project.ch.dto.AvailableMenuDto;
import com.project.ch.dto.CoffeeHouseDto;
import com.project.ch.service.CoffeeHouseService;
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

import static com.project.ch.util.SwaggerConst.JSON;
import static com.project.ch.util.SwaggerConst.SUCCESSFUL_CODE;
import static com.project.ch.util.SwaggerConst.SUCCESSFUL_DESCRIPTION;

@Tag(name = "Coffee House")
@RestController
@RequestMapping("/api/v1/coffeehouse")
public class CoffeeHouseController {

    private final CoffeeHouseService coffeeHouseService;

    @Autowired
    public CoffeeHouseController(CoffeeHouseService coffeeHouseService) {
        this.coffeeHouseService = coffeeHouseService;
    }

    @Operation(summary = "Get a list of coffee houses")
    @ApiResponses(value = {
            @ApiResponse(responseCode = SUCCESSFUL_CODE, description = SUCCESSFUL_DESCRIPTION,
                    content = @Content(mediaType = JSON, array = @ArraySchema(schema = @Schema(implementation = CoffeeHouseDto.class))))
    })
    @GetMapping
    public ResponseEntity<?> getCoffeeHouses() {
        return new ResponseEntity<>(coffeeHouseService.getAll(), HttpStatus.OK);
    }

    @Operation(summary = "Get a menu available for ordering at each coffee house")
    @ApiResponses(value = {
            @ApiResponse(responseCode = SUCCESSFUL_CODE, description = SUCCESSFUL_DESCRIPTION,
                    content = @Content(mediaType = JSON, array = @ArraySchema(schema = @Schema(implementation = AvailableMenuDto.class))))
    })
    @GetMapping("/menu")
    public ResponseEntity<?> getAvailableMenu() {
        return new ResponseEntity<>(coffeeHouseService.getAvailableMenu(), HttpStatus.OK);
    }
}
