package com.project.ch.entity;

import com.project.ch.util.CategoryEnumConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Table(name = "categories")
public class Category extends BaseEntity {

    @Column(name = "category_name")
    @Convert(converter = CategoryEnumConverter.class)
    private CategoryEnum categoryName;

    @OneToMany(mappedBy = "category")
    private List<Product> products = new ArrayList<>();

}
