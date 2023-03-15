package com.testproject.coffeehouseapi.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "orders_details")
public class OrderDetails extends BaseEntity {

    @NotNull(message = "{field.notNull}")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @NotNull(message = "{field.notNull}")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @NotNull(message = "{field.notNull}")
    @Positive(message = "{positive.number}")
    @Column(name = "quantity")
    private Integer quantity;

    @NotNull(message = "{field.notNull}")
    @Positive(message = "{positive.number}")
    @Column(name = "cost")
    private Integer cost;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "orders_details_additives",
            joinColumns = @JoinColumn(name = "order_details_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> additives = new HashSet<>();
}
