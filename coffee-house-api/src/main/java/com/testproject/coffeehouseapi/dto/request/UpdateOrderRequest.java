package com.testproject.coffeehouseapi.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.testproject.coffeehouseapi.model.Status;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UpdateOrderRequest {

    @NotNull(message = "{field.notNull}")
    private Long id;

    @NotNull(message = "{field.notNull}")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime closedAt;

    @NotNull(message = "{field.notNull}")
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;
}
