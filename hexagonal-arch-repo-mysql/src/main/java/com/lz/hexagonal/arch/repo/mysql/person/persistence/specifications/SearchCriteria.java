package com.lz.hexagonal.arch.repo.mysql.person.persistence.specifications;

public record SearchCriteria (
    String key,
    String operation,
    Object value
) {}
