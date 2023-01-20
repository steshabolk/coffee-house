package com.testproject.coffeehouseapi.model;

import com.testproject.coffeehouseapi.util.CategoryEnumConverter;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "products_categories")
public class Category extends BaseEntity {

    @Column(name = "category_name")
    @Convert(converter = CategoryEnumConverter.class)
    private CategoryEnum categoryName;

    @OneToMany(mappedBy = "productCategory", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<Product> products = new ArrayList<>();

}
