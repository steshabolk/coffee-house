package com.testproject.coffeehouseapi.controller;

import com.testproject.coffeehouseapi.dto.request.CancelOrderRequest;
import com.testproject.coffeehouseapi.dto.request.ChangeNameRequest;
import com.testproject.coffeehouseapi.dto.request.ChangePasswordRequest;
import com.testproject.coffeehouseapi.dto.request.PlaceOrderRequest;
import com.testproject.coffeehouseapi.exception.RequestException;
import com.testproject.coffeehouseapi.model.Order;
import com.testproject.coffeehouseapi.model.OrderDetails;
import com.testproject.coffeehouseapi.model.User;
import com.testproject.coffeehouseapi.service.OrderService;
import com.testproject.coffeehouseapi.service.UserService;
import com.testproject.coffeehouseapi.util.DtoMapper;
import com.testproject.coffeehouseapi.util.ResponseHelper;
import com.testproject.coffeehouseapi.validator.ValidationErrMsgBuilder;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@Tag(name = "User", description = "User API")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    private final OrderService orderService;
    private final ResponseHelper responseHelper;
    private final DtoMapper dtoMapper;

    @Autowired
    public UserController(UserService userService, OrderService orderService, ResponseHelper responseHelper, DtoMapper dtoMapper) {
        this.userService = userService;
        this.orderService = orderService;
        this.responseHelper = responseHelper;
        this.dtoMapper = dtoMapper;
    }

    @GetMapping("/orders")
    public ResponseEntity<?> getUserOrders(HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        List<Order> orders = orderService.getUserOrders(user.getId());
        return new ResponseEntity<>(responseHelper.getUserOrdersResponse(orders), HttpStatus.OK);
    }

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
        List<Order> orders = orderService.getUserOrders(user.getId());
        return new ResponseEntity<>(responseHelper.getUserOrdersResponse(orders), HttpStatus.OK);
    }

    @PatchMapping("/orders")
    public ResponseEntity<?> cancelOrder(HttpServletRequest request, @RequestBody @Valid CancelOrderRequest cancelOrderRequest,
                                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errMsg = ValidationErrMsgBuilder.buildFieldErrMsg(bindingResult);
            throw new RequestException(errMsg, HttpStatus.BAD_REQUEST);
        }
        orderService.cancelOrder(orderService.findById(cancelOrderRequest.getId()), cancelOrderRequest.getClosedAt());
        User user = (User) request.getAttribute("user");
        List<Order> orders = orderService.getUserOrders(user.getId());
        return new ResponseEntity<>(responseHelper.getUserOrdersResponse(orders), HttpStatus.OK);

    }

    @PostMapping("/settings/name")
    public ResponseEntity<?> changeUserName(HttpServletRequest request, @RequestBody @Valid ChangeNameRequest changeNameRequest,
                                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errMsg = ValidationErrMsgBuilder.buildFieldErrMsg(bindingResult);
            throw new RequestException(errMsg, HttpStatus.BAD_REQUEST);
        }
        User user = (User) request.getAttribute("user");
        userService.changeName(user, changeNameRequest.getName());
        return new ResponseEntity<>(userService.getAuthToken(user), HttpStatus.OK);

    }

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
