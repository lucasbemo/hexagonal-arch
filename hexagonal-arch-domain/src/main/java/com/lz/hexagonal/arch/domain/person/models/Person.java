package com.lz.hexagonal.arch.domain.person.models;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@ToString
public class Person {
    private long id;
    private String name;
    private String email;
}
