package com.project.ch.entity;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Table(name = "coffee_houses")
public class CoffeeHouse extends BaseEntity {

    @NotNull
    @OneToOne
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    private User manager;

    @NotBlank
    @Size(min = 2, max = 64)
    @Column(name = "city")
    private String city;

    @NotBlank
    @Size(min = 2, max = 128)
    @Column(name = "address")
    private String address;

    @ElementCollection
    @CollectionTable(name = "menu",
            joinColumns = {@JoinColumn(name = "coffee_house_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "product_id")
    @Column(name = "is_available")
    private Map<Long, Boolean> availableProducts = new HashMap<>();

    @OneToMany(mappedBy = "coffeeHouse")
    private List<Order> orders = new ArrayList<>();

}
