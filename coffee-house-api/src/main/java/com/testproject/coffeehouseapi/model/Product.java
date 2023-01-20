package com.testproject.coffeehouseapi.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    @NotBlank(message = "{field.notBlank}")
    @Size(min = 2, max = 64, message = "{field.length64}")
    @Column(name = "product_name")
    private String productName;

    @NotNull(message = "{field.notNull}")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_category", referencedColumnName = "id")
    private Category productCategory;

    @NotBlank(message = "{field.notBlank}")
    @Size(min = 2, max = 10, message = "{field.length10}")
    @Column(name = "size_value")
    private String sizeValue;

    @NotNull(message = "{field.notNull}")
    @Positive(message = "{positive.number}")
    @Column(name = "price")
    private Double price;

    @NotBlank(message = "{field.notBlank}")
    @Size(min = 2, max = 250, message = "{description.length}")
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "product", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<OrderDetails> orderDetails = new ArrayList<>();

}
