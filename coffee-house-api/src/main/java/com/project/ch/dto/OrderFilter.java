package com.project.ch.dto;

import com.project.ch.entity.CoffeeHouse;
import com.project.ch.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.project.ch.service.SearchSpecification.Filter;
import static com.project.ch.service.SearchSpecification.QueryOperator.EQUAL;
import static com.project.ch.service.SearchSpecification.QueryOperator.GREATER_THAN_OR_EQUAL_TO;
import static com.project.ch.service.SearchSpecification.QueryOperator.IN;
import static com.project.ch.service.SearchSpecification.QueryOperator.JOIN;
import static com.project.ch.service.SearchSpecification.QueryOperator.LESS_THAN_OR_EQUAL_TO;
import static com.project.ch.service.SearchSpecification.Sort;
import static com.project.ch.service.SearchSpecification.SortDirection;
import static java.util.Objects.nonNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderFilter {

    private Long id;

    private LocalDateTime createdAtFrom;

    private LocalDateTime createdAtTo;

    private LocalDateTime pickUpAtFrom;

    private LocalDateTime pickUpAtTo;

    private LocalDateTime closedAtFrom;

    private LocalDateTime closedAtTo;

    private List<Status> status;

    public List<Filter> buildSpecificationFilter(CoffeeHouse coffeeHouse) {
        List<Filter> filter = new ArrayList<>();
        if (nonNull(coffeeHouse)) {
            filter.add(Filter.builder().field("coffeeHouse").operator(JOIN).value(coffeeHouse.getId()).build());
        }
        if (nonNull(this.getId())) {
            filter.add(Filter.builder().field("id").operator(EQUAL).value(this.getId()).build());
        }
        if (nonNull(this.getCreatedAtFrom())) {
            filter.add(Filter.builder().field("createdAt").operator(GREATER_THAN_OR_EQUAL_TO).value(this.getCreatedAtFrom()).build());
        }
        if (nonNull(this.getCreatedAtTo())) {
            filter.add(Filter.builder().field("createdAt").operator(LESS_THAN_OR_EQUAL_TO).value(this.getCreatedAtTo()).build());
        }
        if (nonNull(this.getPickUpAtFrom())) {
            filter.add(Filter.builder().field("pickUpAt").operator(GREATER_THAN_OR_EQUAL_TO).value(this.getPickUpAtFrom()).build());
        }
        if (nonNull(this.getPickUpAtTo())) {
            filter.add(Filter.builder().field("pickUpAt").operator(LESS_THAN_OR_EQUAL_TO).value(this.getPickUpAtTo()).build());
        }
        if (nonNull(this.getClosedAtFrom())) {
            filter.add(Filter.builder().field("closedAt").operator(GREATER_THAN_OR_EQUAL_TO).value(this.getPickUpAtFrom()).build());
        }
        if (nonNull(this.getClosedAtTo())) {
            filter.add(Filter.builder().field("closedAt").operator(LESS_THAN_OR_EQUAL_TO).value(this.getPickUpAtTo()).build());
        }
        if (nonNull(this.getStatus()) && !this.getStatus().isEmpty()) {
            filter.add(Filter.builder().field("status").operator(IN)
                .values(this.getStatus().stream().map(status -> (Object)status).toList()).build());
        }
        return filter;
    }

    public List<Sort> buildSpecificationSort() {
        return List.of(new Sort("createdAt", SortDirection.DESC));
    }
}
