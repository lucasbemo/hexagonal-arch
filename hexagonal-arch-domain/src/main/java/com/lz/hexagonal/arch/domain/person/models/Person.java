package com.lz.hexagonal.arch.domain.person.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Person {
    private long id;
    private String name;
    private String email;
}
