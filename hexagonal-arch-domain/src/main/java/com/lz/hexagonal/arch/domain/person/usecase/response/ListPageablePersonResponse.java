package com.lz.hexagonal.arch.domain.person.usecase.response;

import com.lz.hexagonal.arch.domain.person.models.Person;

import java.util.List;

public record ListPageablePersonResponse(
    int totalPages,
    long totalElements,
    String sort,
    List<Person> contents
) {}
