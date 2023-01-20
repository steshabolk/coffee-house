package com.testproject.coffeehouseapi.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RegisterRequest {

    @NotBlank(message = "{field.notBlank}")
    @Size(min = 2, max = 64, message = "{field.length64}")
    @Pattern(regexp = "^(?=.*[\\p{L}\\d])[\\p{L}\\d_]*$", message = "{name.pattern.message}")
    private String name;

    @NotBlank(message = "{field.notBlank}")
    @Pattern(regexp = "^7(\\d{10})", message = "{phone.pattern.message}")
    private String phone;

    @NotBlank(message = "{field.notBlank}")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{6,}$", message = "{password.pattern.message}")
    private String password;
}
