package com.project.ch.controller;

import com.project.ch.dto.OrderDto;
import com.project.ch.dto.OrderFilter;
import com.project.ch.dto.ProductAvailabilityDto;
import com.project.ch.dto.request.UpdateCHMenuAvailabilityRequest;
import com.project.ch.entity.CoffeeHouse;
import com.project.ch.entity.Status;
import com.project.ch.exception.ExceptionResponse;
import com.project.ch.service.CoffeeHouseService;
import com.project.ch.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static com.project.ch.util.SwaggerConst.FORBIDDEN_CODE;
import static com.project.ch.util.SwaggerConst.FORBIDDEN_DESCRIPTION;
import static com.project.ch.util.SwaggerConst.INVALID_INPUT_CODE;
import static com.project.ch.util.SwaggerConst.INVALID_INPUT_DESCRIPTION;
import static com.project.ch.util.SwaggerConst.JSON;
import static com.project.ch.util.SwaggerConst.SUCCESSFUL_CODE;
import static com.project.ch.util.SwaggerConst.SUCCESSFUL_DESCRIPTION;
import static com.project.ch.util.SwaggerConst.UNAUTHORIZED_CODE;
import static com.project.ch.util.SwaggerConst.UNAUTHORIZED_DESCRIPTION;

@Tag(name = "Manager")
@SecurityRequirement(name = "Authorization")
@RestController
@RequestMapping("/api/v1/manager")
public class ManagerController {

    private final CoffeeHouseService coffeeHouseService;
    private final OrderService orderService;

    @Autowired
    public ManagerController(CoffeeHouseService coffeeHouseService, OrderService orderService) {
        this.coffeeHouseService = coffeeHouseService;
        this.orderService = orderService;
    }

    @Operation(summary = "Get a coffee house by authorized manager")
    @ApiResponses(value = {
            @ApiResponse(responseCode = SUCCESSFUL_CODE, description = SUCCESSFUL_DESCRIPTION,
                    content = @Content(mediaType = JSON, schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = UNAUTHORIZED_CODE, description = UNAUTHORIZED_DESCRIPTION,
                    content = @Content),
            @ApiResponse(responseCode = FORBIDDEN_CODE, description = FORBIDDEN_DESCRIPTION,
                    content = @Content)
    })
    @GetMapping("/coffeehouse")
    public ResponseEntity<?> getCoffeeHouse() {
        return new ResponseEntity<>(coffeeHouseService.getCHByManagerId(), HttpStatus.OK);
    }

    @Operation(summary = "Get a coffee shop menu with products available for ordering divided into categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = SUCCESSFUL_CODE, description = SUCCESSFUL_DESCRIPTION,
                    content = @Content(mediaType = JSON, array = @ArraySchema(schema = @Schema(implementation = ProductAvailabilityDto.class)))),
            @ApiResponse(responseCode = UNAUTHORIZED_CODE, description = UNAUTHORIZED_DESCRIPTION,
                    content = @Content),
            @ApiResponse(responseCode = FORBIDDEN_CODE, description = FORBIDDEN_DESCRIPTION,
                    content = @Content)
    })
    @GetMapping("/menu")
    public ResponseEntity<?> getCHMenu() {
        Map<String, List<ProductAvailabilityDto>> menu = coffeeHouseService.getCHMenu();
        return new ResponseEntity<>(menu, HttpStatus.OK);
    }

    @Operation(summary = "Update the availability of products for ordering to true/false in the coffee house")
    @ApiResponses(value = {
            @ApiResponse(responseCode = SUCCESSFUL_CODE, description = SUCCESSFUL_DESCRIPTION,
                    content = @Content(mediaType = JSON, array = @ArraySchema(schema = @Schema(implementation = ProductAvailabilityDto.class)))),
            @ApiResponse(responseCode = INVALID_INPUT_CODE, description = INVALID_INPUT_DESCRIPTION,
                    content = @Content(mediaType = JSON, schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = UNAUTHORIZED_CODE, description = UNAUTHORIZED_DESCRIPTION,
                    content = @Content),
            @ApiResponse(responseCode = FORBIDDEN_CODE, description = FORBIDDEN_DESCRIPTION,
                    content = @Content)
    })
    @PatchMapping("/menu")
    public ResponseEntity<?> updateCHMenuAvailability(@RequestBody @Valid UpdateCHMenuAvailabilityRequest updatedAvailability) {
        coffeeHouseService.setMenuAvailability(updatedAvailability.getMenu());
        Map<String, List<ProductAvailabilityDto>> menu = coffeeHouseService.getCHMenu();
        return new ResponseEntity<>(menu, HttpStatus.OK);
    }

    @Operation(summary = "Get the history of coffee house orders by parameters",
            description = "required parameter 'show': active - get active orders in the coffee house; " +
                    "search - get orders filtered by parameters (order id, time intervals of orders creation, pick up and closing dates, orders status)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = SUCCESSFUL_CODE, description = SUCCESSFUL_DESCRIPTION,
                    content = @Content(mediaType = JSON, array = @ArraySchema(schema = @Schema(implementation = OrderDto.class)))),
            @ApiResponse(responseCode = INVALID_INPUT_CODE, description = INVALID_INPUT_DESCRIPTION,
                    content = @Content),
            @ApiResponse(responseCode = UNAUTHORIZED_CODE, description = UNAUTHORIZED_DESCRIPTION,
                    content = @Content),
            @ApiResponse(responseCode = FORBIDDEN_CODE, description = FORBIDDEN_DESCRIPTION,
                    content = @Content)
    })
    @GetMapping("/orders")
    public ResponseEntity<?> getCHOrders(@RequestParam(value = "show") String show,
                                         @RequestParam(value = "id", required = false) Long id,
                                         @RequestParam(value = "createdAtFrom", required = false)
                                         @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime createdAtFrom,
                                         @RequestParam(value = "createdAtTo", required = false)
                                         @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime createdAtTo,
                                         @RequestParam(value = "pickUpAtFrom", required = false)
                                         @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime pickUpAtFrom,
                                         @RequestParam(value = "pickUpAtTo", required = false)
                                         @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime pickUpAtTo,
                                         @RequestParam(value = "closedAtFrom", required = false)
                                         @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime closedAtFrom,
                                         @RequestParam(value = "closedAtTo", required = false)
                                         @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime closedAtTo,
                                         @RequestParam(value = "status", required = false) List<Status> status) {
        List<OrderDto> orders = orderService.getCHOrdersByFilter(
                show,
                OrderFilter.builder()
                        .id(id)
                        .createdAtFrom(createdAtFrom).createdAtTo(createdAtTo)
                        .pickUpAtFrom(pickUpAtFrom).pickUpAtTo(pickUpAtTo)
                        .closedAtFrom(closedAtFrom).closedAtTo(closedAtTo)
                        .status(status)
                        .build());
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @Operation(summary = "Close the active order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = SUCCESSFUL_CODE, description = SUCCESSFUL_DESCRIPTION,
                    content = @Content(mediaType = JSON, array = @ArraySchema(schema = @Schema(implementation = OrderDto.class)))),
            @ApiResponse(responseCode = INVALID_INPUT_CODE, description = INVALID_INPUT_DESCRIPTION,
                    content = @Content(mediaType = JSON, schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = UNAUTHORIZED_CODE, description = UNAUTHORIZED_DESCRIPTION,
                    content = @Content),
            @ApiResponse(responseCode = FORBIDDEN_CODE, description = FORBIDDEN_DESCRIPTION,
                    content = @Content)
    })
    @PatchMapping("/orders/{id}")
    public ResponseEntity<?> closeActiveOrder(@PathVariable(value = "id") Long id,
                                              @RequestParam(value = "status") Status status) {
        CoffeeHouse coffeeHouse = coffeeHouseService.findByManagerId();
        orderService.closeActiveOrder(id, status, coffeeHouse);
        List<OrderDto> orders = orderService.getCHActiveOrders(coffeeHouse);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
