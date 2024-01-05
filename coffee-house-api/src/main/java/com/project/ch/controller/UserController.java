package com.project.ch.controller;

import com.project.ch.dto.OrderDto;
import com.project.ch.dto.request.ChangeNameRequest;
import com.project.ch.dto.request.ChangePasswordRequest;
import com.project.ch.dto.request.PlaceOrderRequest;
import com.project.ch.entity.User;
import com.project.ch.exception.ExceptionResponse;
import com.project.ch.security.JwtUtil;
import com.project.ch.service.OrderService;
import com.project.ch.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.project.ch.util.SwaggerConst.INVALID_INPUT_CODE;
import static com.project.ch.util.SwaggerConst.INVALID_INPUT_DESCRIPTION;
import static com.project.ch.util.SwaggerConst.JSON;
import static com.project.ch.util.SwaggerConst.SUCCESSFUL_CODE;
import static com.project.ch.util.SwaggerConst.SUCCESSFUL_DESCRIPTION;
import static com.project.ch.util.SwaggerConst.UNAUTHORIZED_CODE;
import static com.project.ch.util.SwaggerConst.UNAUTHORIZED_DESCRIPTION;

@Tag(name = "User")
@SecurityRequirement(name = "Authorization")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    private final OrderService orderService;
    private final JwtUtil jwtUtil;

    @Autowired
    public UserController(UserService userService, OrderService orderService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.orderService = orderService;
        this.jwtUtil = jwtUtil;
    }

    @Operation(summary = "Get user orders by authorized user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = SUCCESSFUL_CODE, description = SUCCESSFUL_DESCRIPTION,
                    content = @Content(mediaType = JSON, array = @ArraySchema(schema = @Schema(implementation = OrderDto.class)))),
            @ApiResponse(responseCode = UNAUTHORIZED_CODE, description = UNAUTHORIZED_DESCRIPTION,
                    content = @Content)
    })
    @GetMapping("/orders")
    public ResponseEntity<?> getUserOrders() {
        List<OrderDto> orders = orderService.getUserOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @Operation(summary = "Place a new user order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = SUCCESSFUL_CODE, description = SUCCESSFUL_DESCRIPTION,
                    content = @Content(mediaType = JSON, array = @ArraySchema(schema = @Schema(implementation = OrderDto.class)))),
            @ApiResponse(responseCode = INVALID_INPUT_CODE, description = INVALID_INPUT_DESCRIPTION,
                    content = @Content(mediaType = JSON, schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = UNAUTHORIZED_CODE, description = UNAUTHORIZED_DESCRIPTION,
                    content = @Content)
    })
    @PostMapping("/orders")
    public ResponseEntity<?> saveOrder(@RequestBody @Valid PlaceOrderRequest placeOrderRequest) {
        orderService.saveOrder(placeOrderRequest);
        List<OrderDto> orders = orderService.getUserOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @Operation(summary = "Cancel the active order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = SUCCESSFUL_CODE, description = SUCCESSFUL_DESCRIPTION,
                    content = @Content(mediaType = JSON, array = @ArraySchema(schema = @Schema(implementation = OrderDto.class)))),
            @ApiResponse(responseCode = INVALID_INPUT_CODE, description = INVALID_INPUT_DESCRIPTION,
                    content = @Content(mediaType = JSON, schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = UNAUTHORIZED_CODE, description = UNAUTHORIZED_DESCRIPTION,
                    content = @Content)
    })
    @PatchMapping("/orders/{id}")
    public ResponseEntity<?> cancelOrder(@PathVariable("id") Long id) {
        orderService.cancelActiveOrder(id);
        List<OrderDto> orders = orderService.getUserOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @Operation(summary = "Change User name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = SUCCESSFUL_CODE, description = SUCCESSFUL_DESCRIPTION + ". You get new JWT in Authentication header in response",
                    content = @Content),
            @ApiResponse(responseCode = INVALID_INPUT_CODE, description = INVALID_INPUT_DESCRIPTION,
                    content = @Content(mediaType = JSON, schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = UNAUTHORIZED_CODE, description = UNAUTHORIZED_DESCRIPTION,
                    content = @Content)
    })
    @PostMapping("/settings/name")
    public ResponseEntity<?> changeUserName(@RequestBody @Valid ChangeNameRequest changeNameRequest,
                                            HttpServletResponse response) {
        User user = userService.changeName(changeNameRequest.getName());
        jwtUtil.setAuthHeader(response, user);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @Operation(summary = "Change User password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = SUCCESSFUL_CODE, description = SUCCESSFUL_DESCRIPTION,
                    content = @Content),
            @ApiResponse(responseCode = INVALID_INPUT_CODE, description = INVALID_INPUT_DESCRIPTION,
                    content = @Content(mediaType = JSON, schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = UNAUTHORIZED_CODE, description = UNAUTHORIZED_DESCRIPTION,
                    content = @Content)
    })
    @PostMapping("/settings/password")
    public ResponseEntity<?> changeUserPassword(@RequestBody @Valid ChangePasswordRequest changePasswordRequest) {
        userService.changePassword(changePasswordRequest.getPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
