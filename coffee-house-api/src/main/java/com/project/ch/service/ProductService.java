package com.project.ch.service;

import com.project.ch.entity.BaseEntity;
import com.project.ch.entity.Product;
import com.project.ch.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public Map<Long, Product> getProductMap(Collection<Long> ids) {
        return toProductMap(productRepository.findByIdIn(ids));
    }

    public Map<Long, Product> toProductMap(List<Product> products) {
        return products.stream().collect(Collectors.toMap(BaseEntity::getId, Function.identity()));
    }
}
