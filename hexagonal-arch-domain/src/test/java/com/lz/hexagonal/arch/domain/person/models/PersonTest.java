package com.lz.hexagonal.arch.domain.person.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PersonTest {

    private LocalDateTime createdAt;
    private LocalDate birthDate;

    @BeforeAll
    static void setup(){
        System.out.println("@BeforeAll executed");
    }

    @BeforeEach
    void setupThis(){
        System.out.println("@BeforeEach executed");
        createdAt = LocalDateTime.now();
        birthDate = LocalDate.now();
    }
    
    @Test
    public void when_createPerson_expect_inputValuesIsSameAttributes() {
        var person = new Person(
                1L, "fulano", "fulano@email.com",
                "12345678913", "11999999999", birthDate, createdAt);
        Assertions.assertEquals(person.createAt(), createdAt);
        Assertions.assertEquals(person.birthDate(), birthDate);
        Assertions.assertEquals(person.phone(), "11999999999");
        Assertions.assertEquals(person.cpf(), "12345678913");
        Assertions.assertEquals(person.email(), "fulano@email.com");
        Assertions.assertEquals(person.name(), "fulano");
        Assertions.assertEquals(person.id(), 1L);
    }
}
