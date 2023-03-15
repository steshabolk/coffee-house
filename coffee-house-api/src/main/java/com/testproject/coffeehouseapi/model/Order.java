package com.testproject.coffeehouseapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@NamedEntityGraph(
        name = "graph.Order.orderDetails",
        attributeNodes = {
                @NamedAttributeNode(value = "orderDetails", subgraph = "graph.OrderDetails")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "graph.OrderDetails",
                        attributeNodes = {
                                @NamedAttributeNode(value = "product")
                        })
        })
@Table(name = "orders")
public class Order extends BaseEntity {

    @NotNull(message = "{field.notNull}")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @NotNull(message = "{field.notNull}")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coffeehouse_id", referencedColumnName = "id")
    private CoffeeHouse coffeeHouse;

    @NotNull(message = "{field.notNull}")
    @Positive(message = "{positive.number}")
    @Column(name = "total_cost")
    private Integer totalCost;

    @NotNull(message = "{field.notNull}")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @NotNull(message = "{field.notNull}")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "pick_up_at")
    private LocalDateTime pickUpAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "closed_at")
    private LocalDateTime closedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @OneToMany(mappedBy = "order", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private List<OrderDetails> orderDetails = new ArrayList<>();
}
