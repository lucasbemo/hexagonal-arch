package com.lz.hexagonal.arch.domain.person.models;

import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record Person(Long id, @NotBlank String name, @NotBlank String email,
                     @NotBlank @CPF String cpf, @NotBlank String phone,
                     @NotNull LocalDate birthDate, LocalDateTime createAt) {
}
