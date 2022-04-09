package com.lz.hexagonal.arch.person.in.web.adapters.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lz.hexagonal.arch.domain.person.models.Person;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.*;
import java.time.LocalDate;
import com.lz.hexagonal.arch.person.mappers.PersonApplicationMapperImpl;

public record CreatePersonRequestWeb(
        @Size(min = 5, max = 100, message = "name must be between 5 and 100 characters")
        @NotBlank(message = "Name cannot be null")
        String name,
        @Size(min = 5, max = 100, message = "email must be between 5 and 100 characters")
        @NotBlank @Email String email,
        @NotBlank @CPF String cpf,
        @Size(min = 5, max = 100, message = "phone must be between 5 and 100 characters")
        @NotBlank String phone,
        @JsonFormat(pattern="yyyy-MM-dd")
        @NotNull LocalDate birthDate,
        @Min(value = 1900, message = "yearBirth must be between 1900 and 2022")
        @Max(value = 2022, message = "yearBirth must be between 1900 and 2022")
        @NotNull int yearBirth) {

    public Person toPerson() {
        return new PersonApplicationMapperImpl().toPerson(this);
    }
}
