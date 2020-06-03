package ru.eracom.persist.repo;

import org.springframework.data.jpa.domain.Specification;
import ru.eracom.persist.entity.Product;

import java.math.BigDecimal;

public final class ProductSpecification {
    public static Specification<Product> trueLiteral() {
        return (root, query, builder) -> builder.isTrue(builder.literal(true));
    }
    public static Specification<Product> costGreaterThanOrEqual(BigDecimal minCost) {
        return (root, query, builder) -> builder.greaterThanOrEqualTo(root.get("minCost"), minCost);
    }
    public static Specification<Product> costLessThanOrEqual(BigDecimal maxCost) {
        return (root, query, builder) -> builder.lessThanOrEqualTo(root.get("maxCost"), maxCost);
    }
    public static Specification<Product> findByProductTitle(String title) {
        return (root, query, builder) -> builder.like(root.get("title"), "%" + title + "%");
    }
}