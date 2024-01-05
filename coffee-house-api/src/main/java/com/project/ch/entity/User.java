package com.project.ch.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
@Table(name = "users")
public class User extends BaseEntity {

    @NotNull
    @Size(min = 2, max = 64)
    @Pattern(regexp = "^[\\p{L}\\d\\s_-]*$", message = "{api.validation.name.message}")
    @Column(name = "user_name")
    private String name;

    @NotNull
    @Pattern(regexp = "^7(\\d{10})$", message = "{api.validation.phone.message}")
    @Column(name = "phone")
    private String phone;

    @NotNull
    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Order> orders = new ArrayList<>();

}
