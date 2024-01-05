package com.project.ch.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @NotNull
    @Size(min = 2, max = 64)
    @Column(name = "product_name")
    private String productName;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "category", referencedColumnName = "id")
    private Category category;

    @NotNull
    @Size(min = 2, max = 10)
    @Column(name = "size_value")
    private String sizeValue;

    @NotNull
    @Positive
    @Column(name = "price")
    private Integer price;

    @NotBlank
    @Size(min = 2, max = 250)
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "product")
    private List<OrderDetails> orderDetails = new ArrayList<>();

}
