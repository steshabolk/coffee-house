package com.testproject.coffeehouseapi.service;

import com.testproject.coffeehouseapi.dto.AvailableMenuDto;
import com.testproject.coffeehouseapi.dto.UpdatedAvailabilityDto;
import com.testproject.coffeehouseapi.dto.response.CoffeeHouseMenuResponse;
import com.testproject.coffeehouseapi.exception.ExceptionMessage;
import com.testproject.coffeehouseapi.exception.MessageConstant;
import com.testproject.coffeehouseapi.exception.RequestException;
import com.testproject.coffeehouseapi.model.CoffeeHouse;
import com.testproject.coffeehouseapi.model.Product;
import com.testproject.coffeehouseapi.model.User;
import com.testproject.coffeehouseapi.repository.CoffeeHouseRepository;
import com.testproject.coffeehouseapi.repository.ProductRepository;
import com.testproject.coffeehouseapi.util.ResponseHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;
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
            throw new RequestException(ExceptionMessage.setMessage(MessageConstant.COFFEE_HOUSE_NOT_FOUND), HttpStatus.BAD_REQUEST);
        });
    }

    public List<CoffeeHouse> findAll() {
        return coffeeHouseRepository.findAll();
    }

    public CoffeeHouse findByManagerId(User user) {
        return coffeeHouseRepository.findByManagerId(user.getId());
    }

    public List<AvailableMenuDto> findAvailableMenu() {
        Map<Long, Product> productsMap = productService.getProductsMap(productRepository.findAll());
        return findAll().stream()
                .map(coffeeHouse -> responseHelper.getAvailableMenuDto(coffeeHouse, filterAvailableProducts(coffeeHouse, productsMap)))
                .collect(Collectors.toList());
    }

    public CoffeeHouseMenuResponse findCoffeeHouseMenu(CoffeeHouse coffeeHouse) {
        List<Product> products = productRepository.findByProductIds(coffeeHouse.getAvailableProducts().keySet());
        return responseHelper.getCoffeeHouseMenu(coffeeHouse, products);
    }

    @Transactional
    public void setMenuAvailability(CoffeeHouse coffeeHouse, List<UpdatedAvailabilityDto> updatedAvailability) {
        List<Long> updatedIds = updatedAvailability.stream().map(UpdatedAvailabilityDto::getId).toList();
        if (!coffeeHouse.getAvailableProducts().keySet().containsAll(updatedIds)) {
            log.info("Error when updating the availability of the Coffee House menu (id = {}), the specified product ids were not found {}",
                    coffeeHouse.getId(), updatedIds);
            throw new RequestException(ExceptionMessage.setMessage(MessageConstant.PRODUCTS_NOT_FOUND), HttpStatus.BAD_REQUEST);
        }
        updatedAvailability.forEach(product -> setProductAvailability(coffeeHouse, product.getId(), product.getAvailability()));
    }

    private List<Product> filterAvailableProducts(CoffeeHouse coffeeHouse, Map<Long, Product> productsMap) {
        Set<Long> productIds = findCoffeeHouseAvailableProductsIds(coffeeHouse);
        return productIds.stream()
                .map(productsMap::get)
                .collect(Collectors.toList());
    }

    public Set<Long> findCoffeeHouseAvailableProductsIds(CoffeeHouse coffeeHouse) {
        return coffeeHouse.getAvailableProducts().entrySet().stream()
                .filter(entry -> entry.getValue().equals(true))
                .map(Map.Entry::getKey).collect(Collectors.toSet());
    }

    private void setProductAvailability(CoffeeHouse coffeeHouse, Long productId, Boolean availability) {
        coffeeHouse.getAvailableProducts().entrySet().stream()
                .filter(entry -> entry.getKey().equals(productId))
                .forEach(entry -> entry.setValue(availability));
        log.info("The availability of product (id = {}) in the Coffee House (id = {}) has been changed to {} at {}",
                productId, coffeeHouse.getId(), availability, responseHelper.currentDateTime());
    }
}
