package com.testproject.coffeehouseapi.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AvailabilityOfProductsRequest {

    @NotNull(message = "{field.notNull}")
    private List<Long> notAvailableProducts;

    @NotNull(message = "{field.notNull}")
    private List<Long> availableProducts;
}
