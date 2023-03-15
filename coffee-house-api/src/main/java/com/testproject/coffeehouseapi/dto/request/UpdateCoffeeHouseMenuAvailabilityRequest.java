package com.testproject.coffeehouseapi.dto.request;

import com.testproject.coffeehouseapi.dto.UpdatedAvailabilityDto;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UpdateCoffeeHouseMenuAvailabilityRequest {

    @NotNull(message = "{field.notNull}")
    @NotEmpty(message = "{field.notEmpty}")
    private List<UpdatedAvailabilityDto> updatedAvailability;
}
