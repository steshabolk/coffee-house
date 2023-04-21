package com.testproject.coffeehouseapi.controller;

import com.testproject.coffeehouseapi.dto.request.ChangeNameRequest;
import com.testproject.coffeehouseapi.dto.request.ChangePasswordRequest;
import com.testproject.coffeehouseapi.dto.request.PlaceOrderRequest;
import com.testproject.coffeehouseapi.dto.request.UpdateOrderRequest;
import com.testproject.coffeehouseapi.dto.response.OrdersResponse;
import com.testproject.coffeehouseapi.exception.ExceptionResponse;
import com.testproject.coffeehouseapi.exception.RequestException;
import com.testproject.coffeehouseapi.model.Order;
import com.testproject.coffeehouseapi.model.OrderDetails;
import com.testproject.coffeehouseapi.model.Status;
import com.testproject.coffeehouseapi.model.User;
import com.testproject.coffeehouseapi.security.JwtUtil;
import com.testproject.coffeehouseapi.service.OrderService;
import com.testproject.coffeehouseapi.service.UserService;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@Tag(name = "User", description = "User API")
@SecurityRequirement(name = "Authorization")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    private final OrderService orderService;
    private final ResponseHelper responseHelper;
    private final DtoMapper dtoMapper;
    private final JwtUtil jwtUtil;

    @Autowired
    public UserController(UserService userService, OrderService orderService, ResponseHelper responseHelper, DtoMapper dtoMapper, JwtUtil jwtUtil) {
        this.userService = userService;
        this.orderService = orderService;
        this.responseHelper = responseHelper;
        this.dtoMapper = dtoMapper;
        this.jwtUtil = jwtUtil;
    }

    @Operation(summary = "Get all orders by authorized user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of all User orders is received successfully",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = OrdersResponse.class)))),
            @ApiResponse(responseCode = "401", description = "Authentication error. User not found",
                    content = @Content)
    })
    @GetMapping("/orders")
    public ResponseEntity<?> getUserOrders(HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        List<Order> orders = orderService.getUserOrders(user);
        return new ResponseEntity<>(responseHelper.getOrdersResponse(orders), HttpStatus.OK);
    }

    @Operation(summary = "Place a new user order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New order placed successfully; you get an updated list of all User orders in response",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = OrdersResponse.class)))),
            @ApiResponse(responseCode = "400", description = "Invalid Request Body",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ExceptionResponse.class)))),
            @ApiResponse(responseCode = "401", description = "Authentication error. User not found",
                    content = @Content)
    })
    @PostMapping("/orders")
    public ResponseEntity<?> placeOrder(HttpServletRequest request, @RequestBody @Valid PlaceOrderRequest placeOrderRequest,
                                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errMsg = ValidationErrMsgBuilder.buildFieldErrMsg(bindingResult);
            throw new RequestException(errMsg, HttpStatus.BAD_REQUEST);
        }
        User user = (User) request.getAttribute("user");
        Order requestOrder = dtoMapper.convertToOrder(placeOrderRequest.getOrder());
        List<OrderDetails> orderDetails =
                placeOrderRequest.getOrderDetails().stream()
                        .map(dtoMapper::convertToOrderDetails)
                        .toList();
        orderService.placeOrder(user, requestOrder, orderDetails);
        List<Order> orders = orderService.getUserOrders(user);
        return new ResponseEntity<>(responseHelper.getOrdersResponse(orders), HttpStatus.OK);
    }

    @Operation(summary = "Cancel the active order",
            description = "Set the order closing date and update the order status to cancelled")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The order is cancelled successfully; you get an updated list of all User orders in response",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = OrdersResponse.class)))),
            @ApiResponse(responseCode = "400", description = "Invalid Request Body or the specified order id was not found",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ExceptionResponse.class)))),
            @ApiResponse(responseCode = "401", description = "Authentication error. User not found",
                    content = @Content)
    })
    @PatchMapping("/orders")
    public ResponseEntity<?> cancelOrder(HttpServletRequest request, @RequestBody @Valid UpdateOrderRequest updateOrderRequest,
                                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errMsg = ValidationErrMsgBuilder.buildFieldErrMsg(bindingResult);
            throw new RequestException(errMsg, HttpStatus.BAD_REQUEST);
        }
        User user = (User) request.getAttribute("user");
        Order order = orderService.findById(updateOrderRequest.getId());
        if (order.getStatus().equals(Status.ACTIVE) && updateOrderRequest.getStatus().equals(Status.CANCELLED) && order.getUser().getId().equals(user.getId())) {
            orderService.closeActiveOrder(order, updateOrderRequest.getClosedAt(), updateOrderRequest.getStatus());
            List<Order> orders = orderService.getUserOrders(user);
            return new ResponseEntity<>(responseHelper.getOrdersResponse(orders), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Change User name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The User name changed successfully; " +
                    "You get new JWT token in Authentication HTTP header in response",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid Request Body",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ExceptionResponse.class)))),
            @ApiResponse(responseCode = "401", description = "Authentication error. User not found",
                    content = @Content)
    })
    @PostMapping("/settings/name")
    public ResponseEntity<?> changeUserName(HttpServletRequest request, HttpServletResponse response,
                                            @RequestBody @Valid ChangeNameRequest changeNameRequest,
                                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errMsg = ValidationErrMsgBuilder.buildFieldErrMsg(bindingResult);
            throw new RequestException(errMsg, HttpStatus.BAD_REQUEST);
        }
        User user = (User) request.getAttribute("user");
        userService.changeName(user, changeNameRequest.getName());
        jwtUtil.setAuthHeader(response, user.getPhone(), user.getName(), user.getRole().name());
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @Operation(summary = "Change User password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The User password changed successfully",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid Request Body",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ExceptionResponse.class)))),
            @ApiResponse(responseCode = "401", description = "Authentication error. User not found",
                    content = @Content)
    })
    @PostMapping("/settings/password")
    public ResponseEntity<?> changeUserPassword(HttpServletRequest request, @RequestBody @Valid ChangePasswordRequest changePasswordRequest,
                                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errMsg = ValidationErrMsgBuilder.buildFieldErrMsg(bindingResult);
            throw new RequestException(errMsg, HttpStatus.BAD_REQUEST);
        }
        User user = (User) request.getAttribute("user");
        userService.changePassword(user, changePasswordRequest.getPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
