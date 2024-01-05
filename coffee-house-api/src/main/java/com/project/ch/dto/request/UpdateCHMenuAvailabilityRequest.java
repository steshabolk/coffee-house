package com.project.ch.dto.request;

import com.project.ch.dto.UpdatedAvailabilityDto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UpdateCHMenuAvailabilityRequest {

    @NotNull
    @NotEmpty
    private List<UpdatedAvailabilityDto> menu;
}
