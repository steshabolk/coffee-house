package com.testproject.coffeehouseapi.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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

    @NotBlank(message = "{field.notBlank}")
    @Size(min = 2, max = 64, message = "{field.length64}")
    @Pattern(regexp = "^(?=.*[\\p{L}\\d])[\\p{L}\\d_]*$", message = "{name.pattern.message}")
    @Column(name = "user_name")
    private String name;

    @NotBlank(message = "{field.notBlank}")
    @Pattern(regexp = "^7(\\d{10})$", message = "{phone.pattern.message}")
    @Column(name = "phone")
    private String phone;

    @NotBlank(message = "{field.notBlank}")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{6,}$", message = "{password.pattern.message}")
    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Order> orders = new ArrayList<>();

}
