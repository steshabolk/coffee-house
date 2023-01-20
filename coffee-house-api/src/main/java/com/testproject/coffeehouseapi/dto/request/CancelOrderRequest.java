package com.testproject.coffeehouseapi.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.testproject.coffeehouseapi.dto.PlaceOrderDetailsDto;
import com.testproject.coffeehouseapi.dto.PlaceOrderDto;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CancelOrderRequest {

    @NotNull(message = "{field.notNull}")
    private Long id;

    @NotNull(message = "{field.notNull}")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime closedAt;
}
