package com.testproject.coffeehouseapi.service;

import com.testproject.coffeehouseapi.exception.MessageConstant;
import com.testproject.coffeehouseapi.exception.RequestException;
import com.testproject.coffeehouseapi.model.*;
import com.testproject.coffeehouseapi.repository.OrderDetailsRepository;
import com.testproject.coffeehouseapi.repository.OrderRepository;
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
    private final ProductService productService;
    private final OrderDetailsRepository orderDetailsRepository;
    private final CoffeeHouseService coffeeHouseService;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderDetailsRepository orderDetailsRepository,
                        ProductService productService, CoffeeHouseService coffeeHouseService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.orderDetailsRepository = orderDetailsRepository;
        this.coffeeHouseService = coffeeHouseService;
    }

    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> {
            log.info("Fail while finding Order with id {}", id);
            throw new RequestException(MessageConstant.ORDER_NOT_FOUND, HttpStatus.BAD_REQUEST);
        });
    }

    public List<Order> getUserOrders(Long id) {
        List<Order> orders = orderRepository.findAllByUserIdOrderByCreatedAt(id);
        List<Long> ids = new ArrayList<>();
        orders.forEach(order -> {
                    order.getOrderDetails().forEach(orderDetail -> {
                        ids.add(orderDetail.getId());
                    });
                });
        List<OrderDetails> orderDetails = orderDetailsRepository.findByOrderDetailsIds(ids);
        Map<Long, Set<Product>> map = new HashMap<>();
        orderDetails.forEach(orderDetail -> {
            map.put(orderDetail.getId(), orderDetail.getAdditives());
        });
        orders.forEach(order -> order.getOrderDetails().forEach(orderDetail -> {
            orderDetail.setAdditives(map.get(orderDetail.getId()));
        }));
        return orders;
    }

    @Transactional
    public void placeOrder(User user, Order requestOrder, List<OrderDetails> orderDetails) {
        Order order = saveOrder(user, requestOrder);
        saveOrderDetails(order, orderDetails);
        log.info("User {} placed new order {}", user.getPhone(), order.getId());
    }

    public Order saveOrder(User user, Order requestOrder) {
        requestOrder.setUser(user);
        requestOrder.setCoffeeHouse(coffeeHouseService.findById(requestOrder.getCoffeeHouse().getId()));
        requestOrder.setStatus(Status.ACTIVE);
        requestOrder.setOrderDetails(new ArrayList<>());
        return orderRepository.save(requestOrder);
    }

    public void saveOrderDetails(Order order, List<OrderDetails> requestOrderDetails) {
        Set<Long> orderProductsIds = new HashSet<>();
        requestOrderDetails.forEach(orderDetail -> {
            orderProductsIds.add(orderDetail.getProduct().getId());
            orderDetail.getAdditives().forEach(additive -> {
                orderProductsIds.add(additive.getId());
            });
        });
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

    @Transactional
    public void cancelOrder(Order order, LocalDateTime closedAt) {
        order.setStatus(Status.CANCELLED);
        order.setClosedAt(closedAt);
        orderRepository.saveAndFlush(order);
    }
}
