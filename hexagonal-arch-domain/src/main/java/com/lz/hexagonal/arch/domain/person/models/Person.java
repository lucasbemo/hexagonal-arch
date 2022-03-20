package com.lz.hexagonal.arch.domain.person.models;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class Person {
    private final Long id;
    private final String name;
    private final String email;
    private final int yearBirth;
    private final LocalDateTime createAt;

    public Person(final Long id, final String name, final String email, final int yearBirth, final LocalDateTime createAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.yearBirth = yearBirth;
        this.createAt = (createAt == null)? LocalDateTime.now(): createAt;
    }
}
