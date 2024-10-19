package com.lz.hexagonal.arch.domain.person.dtos;

import com.lz.hexagonal.arch.domain.person.models.Person;

import java.util.List;

public record ListPageablePersonDTO(
    int totalPages,
    long totalElements,
    String sort,
    List<Person> contents
) {}
