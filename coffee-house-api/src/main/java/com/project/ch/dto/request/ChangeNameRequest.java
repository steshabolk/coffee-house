package com.project.ch.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ChangeNameRequest {

    @NotNull
    @Size(min = 2, max = 64)
    @Pattern(regexp = "^[\\p{L}\\d\\s_-]*$", message = "{api.validation.name.message}")
    private String name;

}
