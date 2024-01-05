package com.project.ch.service;

import com.project.ch.dto.OrderDto;
import com.project.ch.dto.OrderFilter;
import com.project.ch.dto.request.PlaceOrderRequest;
import com.project.ch.entity.BaseEntity;
import com.project.ch.entity.CoffeeHouse;
import com.project.ch.entity.Order;
import com.project.ch.entity.OrderDetails;
import com.project.ch.entity.Product;
import com.project.ch.entity.Status;
import com.project.ch.entity.User;
import com.project.ch.exception.ApiError;
import com.project.ch.exception.ApiResponseStatus;
import com.project.ch.repository.OrderDetailsRepository;
import com.project.ch.repository.OrderRepository;
import com.project.ch.util.DtoMapper;
import com.project.ch.util.Helper;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.project.ch.service.SearchSpecification.Filter;
import static com.project.ch.service.SearchSpecification.Sort;


@Service
public class OrderService {

    Logger log = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository orderRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private final ProductService productService;
    private final CoffeeHouseService coffeeHouseService;
    private final UserService userService;
    private final DtoMapper dtoMapper;
    private final Helper helper;

    private final EntityManager entityManager;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderDetailsRepository orderDetailsRepository,
                        ProductService productService, CoffeeHouseService coffeeHouseService, UserService userService, DtoMapper dtoMapper, Helper helper, EntityManager entityManager) {
        this.orderRepository = orderRepository;
        this.orderDetailsRepository = orderDetailsRepository;
        this.productService = productService;
        this.coffeeHouseService = coffeeHouseService;
        this.userService = userService;
        this.dtoMapper = dtoMapper;
        this.helper = helper;
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = true)
    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> {
            throw ApiError.ORDER_NOT_FOUND.toException(id);
        });
    }

    @Transactional(readOnly = true)
    public List<OrderDto> getUserOrders() {
        List<Order> orders = orderRepository.findByUserIdOrderByCreatedAt(userService.getUserFromAuthentication().getId());
        setOrderDetailsForOrders(orders);
        return orders.stream()
                .map(dtoMapper::convertToOrderDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<OrderDto> getCHOrdersByFilter(String show, OrderFilter orderFilter) {
        CoffeeHouse coffeeHouse = coffeeHouseService.findByManagerId();
        return switch (show) {
            case "active" -> getCHActiveOrders(coffeeHouse);
            case "search" -> getOrdersByFilter(coffeeHouse, orderFilter);
            default -> throw new ApiResponseStatus(HttpStatus.BAD_REQUEST);
        };
    }

    @Transactional(readOnly = true)
    public List<OrderDto> getCHActiveOrders(CoffeeHouse coffeeHouse) {
        List<Order> orders = orderRepository.findByCoffeeHouseIdAndStatusOrderByPickUpAt(coffeeHouse.getId(), Status.ACTIVE);
        setOrderDetailsForOrders(orders);
        return orders.stream()
                .map(dtoMapper::convertToOrderDto)
                .toList();
    }

    private List<OrderDto> getOrdersByFilter(CoffeeHouse coffeeHouse, OrderFilter orderFilter) {
        List<Filter> filters = orderFilter.buildSpecificationFilter(coffeeHouse);
        List<Sort> sorts = orderFilter.buildSpecificationSort();
        List<Order> orders = orderRepository.findAll(new SearchSpecification<>(filters, sorts));
        setOrderDetailsForOrders(orders);
        return orders.stream()
                .map(dtoMapper::convertToOrderDto)
                .toList();
    }

    @Transactional
    public void closeActiveOrder(Long id, Status status, CoffeeHouse coffeeHouse) {
        Order order = findById(id);
        if (!status.equals(Status.CANCELLED) && !status.equals(Status.RECEIVED))
            throw new ApiResponseStatus(HttpStatus.BAD_REQUEST);
        if (!order.getCoffeeHouse().getId().equals(coffeeHouse.getId()))
            throw ApiError.ORDER_NOT_FOUND.toException(order.getId());
        closeOrder(order, status);
    }

    @Transactional
    public void cancelActiveOrder(Long id) {
        User user = userService.getUserFromAuthentication();
        Order order = findById(id);
        if (!order.getUser().getId().equals(user.getId())) throw ApiError.ORDER_NOT_FOUND.toException(order.getId());
        closeOrder(order, Status.CANCELLED);
    }

    @Transactional
    public void saveOrder(PlaceOrderRequest placeOrderRequest) {
        User user = userService.getUserFromAuthentication();
        Order order = dtoMapper.convertToOrder(placeOrderRequest.getOrder());
        List<OrderDetails> orderDetails = placeOrderRequest.getOrderDetails().stream()
                .map(dtoMapper::convertToOrderDetails)
                .toList();
        CoffeeHouse coffeeHouse = coffeeHouseService.findWithMenuById(order.getCoffeeHouse().getId());
        Set<Long> orderProductsIds = orderDetails.stream().map(orderDetail -> orderDetail.getProduct().getId()).collect(Collectors.toSet());
        orderProductsIds.addAll(orderDetails.stream().flatMap(orderDetail -> orderDetail.getAdditives().stream().map(BaseEntity::getId)).collect(Collectors.toSet()));
        checkProductsAvailability(coffeeHouse, orderProductsIds);
        Order savedOrder = saveOrder(user, coffeeHouse, order);
        saveOrderDetails(savedOrder, orderDetails, orderProductsIds);
        log.debug("user{id={}} placed new order{id={}}", user.getId(), order.getId());
        entityManager.clear();
    }

    private void checkProductsAvailability(CoffeeHouse coffeeHouse, Set<Long> orderProductsIds) {
        Set<Long> coffeeHouseAvailableProductsIds = coffeeHouseService.getAvailableProductsIds(coffeeHouse);
        Set<Long> notAvailable = helper.findDifference(orderProductsIds, coffeeHouseAvailableProductsIds);
        if (!notAvailable.isEmpty()) throw ApiError.PRODUCTS_NOT_AVAILABLE.toException(notAvailable);
    }

    private Order saveOrder(User user, CoffeeHouse coffeeHouse, Order order) {
        order.setUser(user);
        order.setCoffeeHouse(coffeeHouse);
        order.setStatus(Status.ACTIVE);
        return orderRepository.saveAndFlush(order);
    }

    private void saveOrderDetails(Order order, List<OrderDetails> orderDetails, Set<Long> orderProductsIds) {
        Map<Long, Product> productMap = productService.getProductMap(orderProductsIds);
        orderDetails.forEach(orderDetail -> {
            orderDetail.setOrder(order);
            orderDetail.setProduct(productMap.get(orderDetail.getProduct().getId()));
            orderDetail.setAdditives(
                    orderDetail.getAdditives().stream()
                            .map(additive -> productMap.get(additive.getId()))
                            .collect(Collectors.toSet()));
        });
        orderDetailsRepository.saveAllAndFlush(orderDetails);
    }

    private void setOrderDetailsForOrders(List<Order> orders) {
        Set<Long> orderDetailsIds = orders.stream().flatMap(order ->
                order.getOrderDetails().stream().map(BaseEntity::getId)).collect(Collectors.toSet());
        Map<Long, OrderDetails> orderDetails = orderDetailsRepository.findByIdIn(orderDetailsIds)
                .stream().collect(Collectors.toMap(OrderDetails::getId, Function.identity()));
        orders.forEach(order -> order.setOrderDetails(
                order.getOrderDetails().stream().map(orderDetail -> orderDetails.get(orderDetail.getId())).toList()));
    }

    private void closeOrder(Order order, Status status) {
        if (!order.getStatus().equals(Status.ACTIVE)) throw ApiError.ORDER_NOT_ACTIVE.toException(order.getId());
        order.setStatus(status);
        order.setClosedAt(LocalDateTime.now());
        orderRepository.saveAndFlush(order);
        switch (status) {
            case CANCELLED -> log.debug("order{id={}} was canceled", order.getId());
            case RECEIVED -> log.debug("order{id={}} was received", order.getId());
        }
    }
}
