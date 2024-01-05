package com.project.ch.dto.request;

import com.project.ch.validator.UniquePhone;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@UniquePhone
public class RegisterRequest {

    @NotNull
    @Size(min = 2, max = 64)
    @Pattern(regexp = "^[\\p{L}\\d\\s_-]*$", message = "{api.validation.name.message}")
    private String name;

    @NotNull
    @Pattern(regexp = "^\\+?[78]?(\\d{10})$", message = "{api.validation.phone.message}")
    private String phone;

    @NotNull
    @Size(min = 6, max = 30)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "{api.validation.password.message}")
    private String password;
}
