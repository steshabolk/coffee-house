package com.project.ch.service;

import com.project.ch.dto.AvailableMenuDto;
import com.project.ch.dto.CoffeeHouseDto;
import com.project.ch.dto.ProductAvailabilityDto;
import com.project.ch.dto.UpdatedAvailabilityDto;
import com.project.ch.entity.CoffeeHouse;
import com.project.ch.entity.Product;
import com.project.ch.exception.ApiError;
import com.project.ch.repository.CoffeeHouseRepository;
import com.project.ch.repository.ProductRepository;
import com.project.ch.util.DtoMapper;
import com.project.ch.util.Helper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CoffeeHouseService {

    Logger log = LoggerFactory.getLogger(CoffeeHouseService.class);

    private final CoffeeHouseRepository coffeeHouseRepository;
    private final ProductRepository productRepository;
    private final ProductService productService;
    private final UserService userService;
    private final Helper helper;
    private final DtoMapper dtoMapper;

    @Autowired
    public CoffeeHouseService(CoffeeHouseRepository coffeeHouseRepository, ProductRepository productRepository, ProductService productService, UserService userService, Helper helper, DtoMapper dtoMapper) {
        this.coffeeHouseRepository = coffeeHouseRepository;
        this.productRepository = productRepository;
        this.productService = productService;
        this.userService = userService;
        this.helper = helper;
        this.dtoMapper = dtoMapper;
    }

    public CoffeeHouse findWithMenuById(Long id) {
        return coffeeHouseRepository.findWithMenuById(id).orElseThrow(() -> {
            throw ApiError.COFFEE_HOUSE_NOT_FOUND.toException(id);
        });
    }

    @Transactional(readOnly = true)
    public List<CoffeeHouse> findAllWithMenu() {
        return coffeeHouseRepository.findAll();
    }

    @Transactional(readOnly = true)
    public CoffeeHouse findByManagerId() {
        return coffeeHouseRepository.findWithMenuByManagerId(userService.getUserFromAuthentication().getId())
                .orElseThrow(() -> {
                    throw ApiError.COFFEE_HOUSE_NOT_FOUND.toException();
                });
    }

    @Transactional(readOnly = true)
    public List<CoffeeHouseDto> getAll() {
        return findAllWithMenu().stream()
                .map(dtoMapper::convertToCHDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<AvailableMenuDto> getAvailableMenu() {
        Map<Long, Product> productMap = productService.toProductMap(productRepository.findAll());
        return findAllWithMenu().stream()
                .map(coffeeHouse -> helper.getAvailableMenuDto(filterProductsByAvailability(coffeeHouse, productMap), coffeeHouse))
                .collect(Collectors.toList());
    }

    public CoffeeHouseDto getCHByManagerId() {
        return dtoMapper.convertToCHDto(findByManagerId());
    }

    @Transactional(readOnly = true)
    public Map<String, List<ProductAvailabilityDto>> getCHMenu() {
        Map<Long, Boolean> availableProducts = findByManagerId().getAvailableProducts();
        List<Product> products = productRepository.findByIdIn(availableProducts.keySet());
        return helper.getCHMenuResponse(products, availableProducts);
    }

    @Transactional
    public void setMenuAvailability(List<UpdatedAvailabilityDto> updatedAvailability) {
        CoffeeHouse coffeeHouse = findByManagerId();
        Map<Long, Boolean> availableProducts = coffeeHouse.getAvailableProducts();
        Set<Long> notFound = helper.findDifference(
                updatedAvailability.stream().map(UpdatedAvailabilityDto::getId).collect(Collectors.toSet()),
                availableProducts.keySet());
        if (!notFound.isEmpty()) throw ApiError.PRODUCTS_NOT_FOUND.toException(notFound);
        log.debug("update the availability of the products in the coffee house{id={}}:", coffeeHouse.getId());
        updatedAvailability.forEach(product -> {
            availableProducts.put(product.getId(), product.getAvailability());
            log.debug("product{id={}} -> {}", product.getId(), product.getAvailability());
        });
    }

    private List<Product> filterProductsByAvailability(CoffeeHouse coffeeHouse, Map<Long, Product> productMap) {
        return coffeeHouse.getAvailableProducts().entrySet().stream()
                .filter(Map.Entry::getValue)
                .map(Map.Entry::getKey)
                .map(productMap::get)
                .collect(Collectors.toList());
    }

    public Set<Long> getAvailableProductsIds(CoffeeHouse coffeeHouse) {
        return coffeeHouse.getAvailableProducts().entrySet().stream()
                .filter(Map.Entry::getValue)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }
}
