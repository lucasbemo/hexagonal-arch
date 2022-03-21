package com.lz.hexagonal.arch.domain.person.models;

import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record Person(Long id, @NotBlank String name, @NotBlank String email,
                     @NotBlank @CPF String cpf, @NotBlank String phone,
                     @NotNull LocalDate birthDate, LocalDateTime createAt) {
    public Person(
            final Long id, final String name, final String email, String cpf,
            final String phone, final LocalDate birthDate, final LocalDateTime createAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.phone = phone;
        this.birthDate = birthDate;
        this.createAt = (createAt == null) ? LocalDateTime.now() : createAt;
    }
}
