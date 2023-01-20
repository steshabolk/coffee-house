package com.testproject.coffeehouseapi.service;

import com.testproject.coffeehouseapi.dto.AllProductsDto;
import com.testproject.coffeehouseapi.exception.MessageConstant;
import com.testproject.coffeehouseapi.exception.RequestException;
import com.testproject.coffeehouseapi.model.CoffeeHouse;
import com.testproject.coffeehouseapi.model.Product;
import com.testproject.coffeehouseapi.repository.CoffeeHouseRepository;
import com.testproject.coffeehouseapi.repository.ProductRepository;
import com.testproject.coffeehouseapi.util.ResponseHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
public class CoffeeHouseService {

    private final CoffeeHouseRepository coffeeHouseRepository;
    private final ProductRepository productRepository;
    private final ProductService productService;
    private final ResponseHelper responseHelper;

    @Autowired
    public CoffeeHouseService(CoffeeHouseRepository coffeeHouseRepository, ProductRepository productRepository, ProductService productService, ResponseHelper responseHelper) {
        this.coffeeHouseRepository = coffeeHouseRepository;
        this.productRepository = productRepository;
        this.productService = productService;
        this.responseHelper = responseHelper;
    }

    public CoffeeHouse findById(Long id) {
        return coffeeHouseRepository.findById(id).orElseThrow(() -> {
            log.info("Fail while finding Coffee House with id {}", id);
            throw new RequestException(MessageConstant.COFFEE_HOUSE_NOT_FOUND, HttpStatus.BAD_REQUEST);
        });
    }

    public List<CoffeeHouse> findAll() {
        return coffeeHouseRepository.findAll();
    }

    public List<AllProductsDto> findAllAvailableProducts() {
        Map<Long, Product> productsMap = productService.getProductsMap(productRepository.findAll());
        List<AllProductsDto> menu = new ArrayList<>();
        findAll().forEach(coffeeHouse -> {
            menu.add(responseHelper.getAllProductsDto(coffeeHouse, findCoffeeHouseAvailableProducts(coffeeHouse, productsMap)));
        });
        return menu;
    }

//    public List<Product> findCoffeeHouseProducts(CoffeeHouse coffeeHouse) {
//        return productRepository.findByProductIds(coffeeHouse.getAvailableProducts().keySet());
//    }

    public List<Product> findCoffeeHouseAvailableProducts(CoffeeHouse coffeeHouse, Map<Long, Product> productsMap) {
        List<Long> productIds = coffeeHouse.getAvailableProducts().entrySet().stream()
                .filter(entry -> entry.getValue().equals(true))
                .map(Map.Entry::getKey).toList();
        return productIds.stream()
                .map(productsMap::get)
                .collect(Collectors.toList());
    }

//    @Transactional
//    public void setProductAvailability(CoffeeHouse coffeeHouse, List<Long> productIds, Boolean available) {
//        productIds.forEach(id -> setAvailability(coffeeHouse, id, available));
//    }
//
//    private void setAvailability(CoffeeHouse coffeeHouse, Long productId, Boolean available) {
//        coffeeHouse.getAvailableProducts().entrySet().stream()
//                .filter(entry -> entry.getKey().equals(productId))
//                .forEach(entry -> entry.setValue(available));
//        log.info("The availability of product (id = {}) in the Coffee House (id = {}) has been changed to {} at {}",
//                productId, coffeeHouse.getId(), available, LocalDateTime.now().format(DateTimeFormatter.ofPattern(MessageConstant.DATE_TIME_PATTERN)));
//    }
}
