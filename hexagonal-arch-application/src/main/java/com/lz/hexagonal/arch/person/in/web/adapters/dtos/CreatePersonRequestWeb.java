package com.lz.hexagonal.arch.person.in.web.adapters.dtos;

import com.lz.hexagonal.arch.domain.person.models.Person;
import com.lz.hexagonal.arch.person.mappers.PersonMapperImpl;

import javax.validation.constraints.*;

public record CreatePersonRequestWeb(
        @Size(min = 5, max = 100, message = "name must be between 5 and 100 characters")
        @NotBlank(message = "Name cannot be null")
        String name,
        @Email @NotBlank String email,
        @Min(value = 1900, message = "yearBirth must be between 1900 and 2022")
        @Max(value = 2022, message = "yearBirth must be between 1900 and 2022")
        @NotNull int yearBirth) {

    public Person toPerson() {
        return new PersonMapperImpl().toPerson(this);
    }
}
