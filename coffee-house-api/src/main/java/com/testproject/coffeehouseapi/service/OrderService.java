package com.testproject.coffeehouseapi.service;

import com.testproject.coffeehouseapi.dto.OrderFilter;
import com.testproject.coffeehouseapi.exception.ExceptionMessage;
import com.testproject.coffeehouseapi.exception.MessageConstant;
import com.testproject.coffeehouseapi.exception.RequestException;
import com.testproject.coffeehouseapi.model.*;
import com.testproject.coffeehouseapi.repository.OrderDetailsRepository;
import com.testproject.coffeehouseapi.repository.OrderRepository;
import com.testproject.coffeehouseapi.util.ResponseHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private final ProductService productService;
    private final CoffeeHouseService coffeeHouseService;
    private final ResponseHelper responseHelper;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderDetailsRepository orderDetailsRepository,
                        ProductService productService, CoffeeHouseService coffeeHouseService,
                        ResponseHelper responseHelper) {
        this.orderRepository = orderRepository;
        this.orderDetailsRepository = orderDetailsRepository;
        this.productService = productService;
        this.coffeeHouseService = coffeeHouseService;
        this.responseHelper = responseHelper;
    }

    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> {
            log.info("Error when finding an order with id {}", id);
            throw new RequestException(ExceptionMessage.setMessage(MessageConstant.ORDER_NOT_FOUND), HttpStatus.BAD_REQUEST);
        });
    }

    public List<Order> getUserOrders(User user) {
        List<Order> orders = orderRepository.findByUserIdOrderByCreatedAt(user.getId());
        return setOrderDetailsForOrders(orders);
    }

    public List<Order> getCoffeeHouseActiveOrders(CoffeeHouse coffeeHouse) {
        List<Order> orders = orderRepository.findByCoffeeHouseIdAndStatusOrderByPickUpAt(coffeeHouse.getId(), Status.ACTIVE);
        return setOrderDetailsForOrders(orders);
    }

    public List<Order> getCoffeeHouseOrdersByFilter(CoffeeHouse coffeeHouse, OrderFilter orderFilter) {
        List<Order> orders = orderRepository.findCoffeeHouseOrdersByFilter(coffeeHouse.getId(), orderFilter);
        return setOrderDetailsForOrders(orders);
    }

    @Transactional
    public void closeActiveOrder(Order order, LocalDateTime closedAt, Status status) {
        order.setStatus(status);
        order.setClosedAt(closedAt);
        orderRepository.saveAndFlush(order);
        if (status.equals(Status.CANCELLED)) {
            log.info("The order (id={}) was canceled at {}", order.getId(), responseHelper.currentDateTime());
        }
        if (status.equals(Status.RECEIVED)) {
            log.info("The order (id={}) was received at {}", order.getId(), responseHelper.currentDateTime());
        }
    }

    @Transactional
    public void placeOrder(User user, Order requestOrder, List<OrderDetails> orderDetails) {
        CoffeeHouse coffeeHouse = coffeeHouseService.findById(requestOrder.getCoffeeHouse().getId());
        Set<Long> orderProductsIds = new HashSet<>();
        orderDetails.forEach(orderDetail -> {
            orderProductsIds.add(orderDetail.getProduct().getId());
            orderDetail.getAdditives().forEach(additive -> orderProductsIds.add(additive.getId()));
        });
        if (checkPlacedOrderAvailability(coffeeHouse, orderProductsIds)) {
            Order order = saveOrder(user, coffeeHouse, requestOrder);
            saveOrderDetails(order, orderDetails, orderProductsIds);
            orderRepository.refresh(order);
            log.info("User {} placed new order (id={}) at {}", user.getPhone(), order.getId(), responseHelper.currentDateTime());
        } else {
            throw new RequestException(ExceptionMessage.setMessage(MessageConstant.ORDER_DETAILS_NOT_AVAILABLE), HttpStatus.BAD_REQUEST);
        }
    }

    private boolean checkPlacedOrderAvailability(CoffeeHouse coffeeHouse, Set<Long> orderProductsIds) {
        Set<Long> coffeeHouseAvailableProductsIds = coffeeHouseService.findCoffeeHouseAvailableProductsIds(coffeeHouse);
        return coffeeHouseAvailableProductsIds.containsAll(orderProductsIds);
    }
    private Order saveOrder(User user, CoffeeHouse coffeeHouse, Order requestOrder) {
        requestOrder.setUser(user);
        requestOrder.setCoffeeHouse(coffeeHouse);
        requestOrder.setStatus(Status.ACTIVE);
        requestOrder.setOrderDetails(new ArrayList<>());
        return orderRepository.save(requestOrder);
    }

    private void saveOrderDetails(Order order, List<OrderDetails> requestOrderDetails, Set<Long> orderProductsIds) {
        Map<Long, Product> productsMap = productService.getProductsMapByIds(orderProductsIds);
        requestOrderDetails.forEach(orderDetail -> {
            orderDetail.setOrder(order);
            orderDetail.setProduct(productsMap.get(orderDetail.getProduct().getId()));
            orderDetail.setAdditives(
                    orderDetail.getAdditives().stream()
                            .map(additive -> productsMap.get(additive.getId()))
                            .collect(Collectors.toSet()));
        });
        orderDetailsRepository.saveAllAndFlush(requestOrderDetails);
    }

    private List<Order> setOrderDetailsForOrders(List<Order> orders) {
        List<Long> orderDetailsIds = new ArrayList<>();
        orders.forEach(order -> order.getOrderDetails()
                .forEach(orderDetail -> orderDetailsIds.add(orderDetail.getId())));
        List<OrderDetails> orderDetails = orderDetailsRepository.findByOrderDetailsIds(orderDetailsIds);
        Map<Long, Set<Product>> map = new HashMap<>();
        orderDetails.forEach(orderDetail -> map.put(orderDetail.getId(), orderDetail.getAdditives()));
        orders.forEach(order -> order.getOrderDetails().forEach(orderDetail ->
                orderDetail.setAdditives(map.get(orderDetail.getId()))));
        return orders;
    }
}
