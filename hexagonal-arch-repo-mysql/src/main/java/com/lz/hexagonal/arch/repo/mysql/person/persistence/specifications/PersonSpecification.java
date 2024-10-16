package com.lz.hexagonal.arch.repo.mysql.person.persistence.specifications;

import com.lz.hexagonal.arch.repo.mysql.person.persistence.entities.PersonEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public record PersonSpecification(SearchCriteria criteria) implements Specification<PersonEntity> {

    @Override
    public Predicate toPredicate(Root<PersonEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (criteria.operation().equalsIgnoreCase(">")) {
            return criteriaBuilder.greaterThanOrEqualTo(
                    root.<String> get(criteria.key()), criteria.value().toString());
        }
        else if (criteria.operation().equalsIgnoreCase("<")) {
            return criteriaBuilder.lessThanOrEqualTo(
                    root.<String> get(criteria.key()), criteria.value().toString());
        }
        else if (criteria.operation().equalsIgnoreCase(":")) {
            if (root.get(criteria.key()).getJavaType() == String.class) {
                return criteriaBuilder.like(
                        root.<String>get(criteria.key()), "%" + criteria.value() + "%");
            } else {
                return criteriaBuilder.equal(root.get(criteria.key()), criteria.value());
            }
        }
        return null;
    }
}
