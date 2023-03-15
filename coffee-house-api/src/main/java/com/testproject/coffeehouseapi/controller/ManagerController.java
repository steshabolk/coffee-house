package com.testproject.coffeehouseapi.controller;

import com.testproject.coffeehouseapi.dto.OrderFilter;
import com.testproject.coffeehouseapi.dto.request.UpdateCoffeeHouseMenuAvailabilityRequest;
import com.testproject.coffeehouseapi.dto.request.UpdateOrderRequest;
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

    @GetMapping("/address")
    public ResponseEntity<?> getCoffeeHouseAddress(HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        return new ResponseEntity<>(
                dtoMapper.convertToCoffeeHouseAddressDto(coffeeHouseService.findByManagerId(user)),
                HttpStatus.OK);
    }

    @GetMapping("/menu")
    public ResponseEntity<?> getCoffeeHouseMenu(HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        return new ResponseEntity<>(coffeeHouseService.findCoffeeHouseMenu(coffeeHouseService.findByManagerId(user)), HttpStatus.OK);
    }

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

    @PatchMapping("/orders")
    public ResponseEntity<?> updateOrderStatus(HttpServletRequest request, @RequestBody @Valid UpdateOrderRequest updateOrderRequest,
                                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errMsg = ValidationErrMsgBuilder.buildFieldErrMsg(bindingResult);
            throw new RequestException(errMsg, HttpStatus.BAD_REQUEST);
        }
        orderService.updateOrderStatus(orderService.findById(updateOrderRequest.getId()), updateOrderRequest.getClosedAt(), updateOrderRequest.getStatus());
        User user = (User) request.getAttribute("user");
        List<Order> orders = orderService.getCoffeeHouseActiveOrders(coffeeHouseService.findByManagerId(user));
        return new ResponseEntity<>(responseHelper.getOrdersResponse(orders), HttpStatus.OK);
    }
}
