package com.velox.userservice.repository.specification;

import com.velox.userservice.models.entity.BaseModel;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GeneticSpecification {
    public static <T extends BaseModel> Specification<T> genericSpecificationFilter(Map<String, Object> data) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            for (Map.Entry<String, Object> map : data.entrySet()) {
                String key = map.getKey();
                Object value = map.getValue();
                if (value != null) {
                    // String fields use partial matching (LIKE %value%), others use exact equals
                    if (value instanceof String) {
                        String stringValue = (String) value;
                        predicates.add(cb.like(cb.lower(root.get(key)), "%" + stringValue.toLowerCase() + "%"));
                    } else {
                        predicates.add(cb.equal(root.get(key), value));
                    }
                }
            }
            // 3. Combine all dynamic queries into a single unified AND block
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    public static <T extends BaseModel> Specification<T> equals(String key, Object value) {
        return (root, query, cb) -> {
            if (key == null || key.isBlank()) {
                cb.conjunction();
            }
            if (key.isEmpty()) {
                cb.conjunction();
            }
            return cb.equal(root.get(key), value);
        };
    }

    public static <T extends BaseModel> Specification<T> like(String key, Object value) {
        return (root, query, cb) -> {
            if (key == null || key.isBlank()) {
                cb.conjunction();
            }
            String pattern = "%" + value.toString().toLowerCase() + "%";
            return cb.like(root.get(key), pattern);
        };
    }
}
