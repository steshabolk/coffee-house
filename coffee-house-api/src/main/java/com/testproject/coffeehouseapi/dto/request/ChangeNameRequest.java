package com.testproject.coffeehouseapi.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ChangeNameRequest {

    @NotBlank(message = "{field.notBlank}")
    @Size(min = 2, max = 64, message = "{field.length64}")
    @Pattern(regexp = "^(?=.*[\\p{L}\\d])[\\p{L}\\d_]*$", message = "{name.pattern.message}")
    private String name;

}
