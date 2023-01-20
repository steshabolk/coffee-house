package com.testproject.coffeehouseapi.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LoginRequest {

    @NotBlank(message = "{field.notBlank}")
    @Pattern(regexp = "^7(\\d{10})", message = "{phone.pattern.message}")
    private String phone;

    @NotBlank(message = "{field.notBlank}")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{6,}$", message = "{password.pattern.message}")
    private String password;

}
