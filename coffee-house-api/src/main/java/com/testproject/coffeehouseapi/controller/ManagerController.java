package com.testproject.coffeehouseapi.controller;

import com.testproject.coffeehouseapi.dto.CoffeeHouseAddressDto;
import com.testproject.coffeehouseapi.dto.OrderFilter;
import com.testproject.coffeehouseapi.dto.request.UpdateCoffeeHouseMenuAvailabilityRequest;
import com.testproject.coffeehouseapi.dto.request.UpdateOrderRequest;
import com.testproject.coffeehouseapi.dto.response.CoffeeHouseMenuResponse;
import com.testproject.coffeehouseapi.dto.response.OrdersResponse;
import com.testproject.coffeehouseapi.exception.ExceptionResponse;
import com.testproject.coffeehouseapi.exception.RequestException;
import com.testproject.coffeehouseapi.model.CoffeeHouse;
import com.testproject.coffeehouseapi.model.Order;
import com.testproject.coffeehouseapi.model.Status;
import com.testproject.coffeehouseapi.model.User;
import com.testproject.coffeehouseapi.service.CoffeeHouseService;
import com.testproject.coffeehouseapi.service.OrderService;
import com.testproject.coffeehouseapi.util.DtoMapper;
import com.testproject.coffeehouseapi.util.ResponseHelper;
import com.testproject.coffeehouseapi.validator.ValidationErrMsgBuilder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Tag(name = "Manager", description = "Manager API")
@SecurityRequirement(name = "Authorization")
@RestController
@RequestMapping("/api/v1/manager")
public class ManagerController {

    private final CoffeeHouseService coffeeHouseService;
    private final OrderService orderService;
    private final DtoMapper dtoMapper;
    private final ResponseHelper responseHelper;

    @Autowired
    public ManagerController(CoffeeHouseService coffeeHouseService, OrderService orderService, DtoMapper dtoMapper, ResponseHelper responseHelper) {
        this.coffeeHouseService = coffeeHouseService;
        this.orderService = orderService;
        this.dtoMapper = dtoMapper;
        this.responseHelper = responseHelper;
    }

    @Operation(summary = "Get coffee house address by authorized manager", description = "Received data : coffee house id, city, address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Coffee house address is received successfully",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CoffeeHouseAddressDto.class)))),
            @ApiResponse(responseCode = "401", description = "Authentication error. User not found",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Authorization error. No permissions to access the resource",
                    content = @Content)
    })
    @GetMapping("/address")
    public ResponseEntity<?> getCoffeeHouseAddress(HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        return new ResponseEntity<>(
                dtoMapper.convertToCoffeeHouseAddressDto(coffeeHouseService.findByManagerId(user)),
                HttpStatus.OK);
    }

    @Operation(summary = "Get coffee house menu with products availability by authorized manager",
            description = "You get a list of menu products with availability for ordering divided into categories " +
                    "for the coffee house found by manager account from request")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The list of menu products with boolean availability key is received successfully",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CoffeeHouseMenuResponse.class)))),
            @ApiResponse(responseCode = "401", description = "Authentication error. User not found",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Authorization error. No permissions to access the resource",
                    content = @Content)
    })
    @GetMapping("/menu")
    public ResponseEntity<?> getCoffeeHouseMenu(HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        return new ResponseEntity<>(coffeeHouseService.findCoffeeHouseMenu(coffeeHouseService.findByManagerId(user)), HttpStatus.OK);
    }

    @Operation(summary = "Update products availability for ordering at the coffee house by authorized manager",
            description = "Change the product availability for ordering key to true/false")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The menu availability update was successful; you get an updated menu in response",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CoffeeHouseMenuResponse.class)))),
            @ApiResponse(responseCode = "400", description = "Error when updating the availability of the Coffee House menu: " +
                    "Invalid Request Body or the specified product ids were not found",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ExceptionResponse.class)))),
            @ApiResponse(responseCode = "401", description = "Authentication error. User not found",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Authorization error. No permissions to access the resource",
                    content = @Content)
    })
    @PatchMapping("/menu")
    public ResponseEntity<?> updateCoffeeHouseMenuAvailability(HttpServletRequest request,
                                                               @RequestBody @Valid UpdateCoffeeHouseMenuAvailabilityRequest updateCoffeeHouseMenuAvailabilityRequest,
                                                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errMsg = ValidationErrMsgBuilder.buildFieldErrMsg(bindingResult);
            throw new RequestException(errMsg, HttpStatus.BAD_REQUEST);
        }
        User user = (User) request.getAttribute("user");
        CoffeeHouse coffeeHouse = coffeeHouseService.findByManagerId(user);
        coffeeHouseService.setMenuAvailability(coffeeHouse, updateCoffeeHouseMenuAvailabilityRequest.getUpdatedAvailability());
        return new ResponseEntity<>(coffeeHouseService.findCoffeeHouseMenu(coffeeHouse), HttpStatus.OK);
    }

    @Operation(summary = "Get the history of Coffee House orders by request parameters",
            description = "Required parameter 'show': active - get all active orders in the Coffee House; " +
                    "search - get orders filtered by parameters (order id, time intervals of orders creation, pick up and closing dates, orders status)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of Coffee House orders filtered by the specified parameters is received successfully",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = OrdersResponse.class)))),
            @ApiResponse(responseCode = "400", description = "Invalid request parameters",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Authentication error. User not found",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Authorization error. No permissions to access the resource",
                    content = @Content)
    })
    @GetMapping("/orders")
    public ResponseEntity<?> getCoffeeHouseOrders(HttpServletRequest request, @RequestParam(value = "show") String show,
                                                  @RequestParam(value = "id", required = false) Long id,
                                                  @RequestParam(value = "createdAt_from", required = false)
                                                            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime createdAt_from,
                                                  @RequestParam(value = "createdAt_to", required = false)
                                                            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime createdAt_to,
                                                  @RequestParam(value = "pickUpAt_from", required = false)
                                                            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime pickUpAt_from,
                                                  @RequestParam(value = "pickUpAt_to", required = false)
                                                            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime pickUpAt_to,
                                                  @RequestParam(value = "closedAt_from", required = false)
                                                            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime closedAt_from,
                                                  @RequestParam(value = "closedAt_to", required = false)
                                                            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime closedAt_to,
                                                  @RequestParam(value = "status", required = false) List<Status> status) {
        List<String> showParam = List.of("active", "search");
        User user = (User) request.getAttribute("user");
        if (showParam.contains(show)) {
            CoffeeHouse coffeeHouse = coffeeHouseService.findByManagerId(user);
            List<Order> orders = switch (show) {
                case "active" -> orderService.getCoffeeHouseActiveOrders(coffeeHouse);
                case "search" -> orderService.getCoffeeHouseOrdersByFilter(coffeeHouse,
                        OrderFilter.builder().id(id)
                        .createdAt_from(createdAt_from).createdAt_to(createdAt_to)
                        .pickUpAt_from(pickUpAt_from).pickUpAt_to(pickUpAt_to)
                        .closedAt_from(closedAt_from).closedAt_to(closedAt_to)
                        .status(status).build());
                default -> List.of();
            };
            return new ResponseEntity<>(responseHelper.getOrdersResponse(orders), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Close the active order",
            description = "Set the order closing date and update the order status to received or cancelled")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The order is closed successfully; you get an updated list of active orders in response",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = OrdersResponse.class)))),
            @ApiResponse(responseCode = "400", description = "Invalid Request Body or the specified order id was not found",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ExceptionResponse.class)))),
            @ApiResponse(responseCode = "401", description = "Authentication error. User not found",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Authorization error. No permissions to access the resource",
                    content = @Content)
    })
    @PatchMapping("/orders")
    public ResponseEntity<?> closeActiveOrder(HttpServletRequest request, @RequestBody @Valid UpdateOrderRequest updateOrderRequest,
                                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errMsg = ValidationErrMsgBuilder.buildFieldErrMsg(bindingResult);
            throw new RequestException(errMsg, HttpStatus.BAD_REQUEST);
        }
        orderService.closeActiveOrder(orderService.findById(updateOrderRequest.getId()), updateOrderRequest.getClosedAt(), updateOrderRequest.getStatus());
        User user = (User) request.getAttribute("user");
        List<Order> orders = orderService.getCoffeeHouseActiveOrders(coffeeHouseService.findByManagerId(user));
        return new ResponseEntity<>(responseHelper.getOrdersResponse(orders), HttpStatus.OK);
    }
}
