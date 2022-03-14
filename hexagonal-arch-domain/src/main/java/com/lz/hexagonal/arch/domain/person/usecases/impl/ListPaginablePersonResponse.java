package com.lz.hexagonal.arch.domain.person.usecases.impl;

import com.lz.hexagonal.arch.domain.person.models.Person;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Builder
@ToString
public class ListPaginablePersonResponse {
    int totalPages;
    long totalElements;
    private String sort;
    private List<Person> contents;
}
