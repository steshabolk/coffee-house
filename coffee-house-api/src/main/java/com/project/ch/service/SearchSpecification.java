package com.project.ch.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

@RequiredArgsConstructor
public class SearchSpecification<T> implements Specification<T> {

    private final List<Filter> filter;
    private final List<Sort> sort;

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.equal(criteriaBuilder.literal(Boolean.TRUE), Boolean.TRUE);
        if (nonNull(filter) && !filter.isEmpty()) {
            for (Filter filter : filter) {
                predicate = filter.getOperator().build(root, criteriaBuilder, filter, predicate);
            }
        }
        if (nonNull(sort) && !sort.isEmpty()) {
            List<Order> orders = new ArrayList<>();
            for (Sort sort : sort) {
                orders.add(sort.getDirection().build(root, criteriaBuilder, sort));
            }
            query.orderBy(orders);
        }
        return predicate;
    }

    @Getter
    @Setter
    @Builder
    public static class Filter {
        private String field;
        private QueryOperator operator;
        private Object value;
        private List<Object> values;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Sort {
        private String field;
        private SortDirection direction;
    }

    public enum QueryOperator {
        EQUAL {
            @Override
            public Predicate build(Root<?> root, CriteriaBuilder criteriaBuilder, Filter filter, Predicate predicate) {
                return criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get(filter.getField()), filter.getValue()));
            }
        },
        GREATER_THAN_OR_EQUAL_TO {
            @Override
            public Predicate build(Root<?> root, CriteriaBuilder criteriaBuilder, Filter filter, Predicate predicate) {
                return criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(root.get(filter.getField()),
                        (LocalDateTime) filter.getValue()));
            }
        },
        LESS_THAN_OR_EQUAL_TO {
            @Override
            public Predicate build(Root<?> root, CriteriaBuilder criteriaBuilder, Filter filter, Predicate predicate) {
                return criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(root.get(filter.getField()),
                        (LocalDateTime) filter.getValue()));
            }
        },
        IN {
            @Override
            public Predicate build(Root<?> root, CriteriaBuilder criteriaBuilder, Filter filter, Predicate predicate) {
                return criteriaBuilder.and(predicate, criteriaBuilder.in(root.get(filter.getField())).value(filter.getValues()));
            }
        },
        JOIN {
            @Override
            Predicate build(Root<?> root, CriteriaBuilder criteriaBuilder, Filter filter, Predicate predicate) {
                return criteriaBuilder.and(predicate, criteriaBuilder.equal(root.join(filter.getField()).get("id"), filter.getValue()));
            }
        };

        abstract Predicate build(Root<?> root, CriteriaBuilder criteriaBuilder, Filter filter, Predicate predicate);
    }

    public enum SortDirection {
        ASC {
            @Override
            Order build(Root<?> root, CriteriaBuilder criteriaBuilder, Sort sort) {
                return criteriaBuilder.asc(root.get(sort.getField()));
            }
        },
        DESC {
            @Override
            Order build(Root<?> root, CriteriaBuilder criteriaBuilder, Sort sort) {
                return criteriaBuilder.desc(root.get(sort.getField()));
            }
        };

        abstract Order build(Root<?> root, CriteriaBuilder criteriaBuilder, Sort sort);
    }

}
