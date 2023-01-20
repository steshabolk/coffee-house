package com.testproject.coffeehouseapi.service;

import com.testproject.coffeehouseapi.model.Product;
import com.testproject.coffeehouseapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Map<Long, Product> getProductsMapByIds(Iterable<Long> ids) {
        List<Product> products = productRepository.findByProductIds(ids);
        return getProductsMap(products);
    }

    public Map<Long, Product> getProductsMap(List<Product> products) {
        Map<Long, Product> productsMap = new HashMap<>();
        products.forEach(product -> {
            productsMap.put(product.getId(), product);
        });
        return productsMap;
    }
}
