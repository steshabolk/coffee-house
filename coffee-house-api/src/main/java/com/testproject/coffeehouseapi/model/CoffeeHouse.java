package com.testproject.coffeehouseapi.model;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "coffeehouses")
public class CoffeeHouse extends BaseEntity {

    @NotNull(message = "{field.notNull}")
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    private User manager;

    @NotBlank(message = "{field.notBlank}")
    @Size(min = 2, max = 64, message = "{field.length64}")
    @Column(name = "city")
    private String city;

    @NotBlank(message = "{field.notBlank}")
    @Size(min = 2, max = 128, message = "{field.length128}")
    @Column(name = "address")
    private String address;

    @ElementCollection
    @CollectionTable(name = "coffeehouses_products",
            joinColumns = {@JoinColumn(name = "coffeehouse_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "product_id")
    @Column(name = "is_available")
    @Cascade(value = CascadeType.SAVE_UPDATE)
    private Map<Long, Boolean> availableProducts = new HashMap<>();

    @OneToMany(mappedBy = "coffeeHouse", cascade = {javax.persistence.CascadeType.PERSIST, javax.persistence.CascadeType.MERGE})
    private List<Order> orders = new ArrayList<>();

}
