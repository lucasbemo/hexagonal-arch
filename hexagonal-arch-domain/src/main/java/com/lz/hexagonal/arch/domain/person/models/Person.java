package com.lz.hexagonal.arch.domain.person.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record Person(
        UUID id,
        @NotBlank(message = "name must not be null or blank") String name,
        @NotBlank(message = "email must not be null or blank") String email,
        @CPF(message = "cpf must not be null or blank")
        @NotBlank(message = "cpf must not be null or blank") String cpf,
        @NotBlank(message = "phone must not be null or blank") String phone,
        @NotNull(message = "birthDate must not be null or blank") LocalDate birthDate,
        LocalDateTime createAt
) {
    static final Logger logger = LoggerFactory.getLogger(Person.class);

    public Person {
        logger.info("creating a Person.");
        executeRules(name, birthDate);
        logger.info("Person created succeed.");
    }

    private static void executeRules(String name, LocalDate birthDate) {
        hasRequiredFields(name);
        hasMinimumAge(birthDate);
        livesInBrasil();
    }

    private static void hasRequiredFields(String name) {
        if (name == null || name.isEmpty())
            logger.info("Name is required");
        // throw new IllegalArgumentException("Name is required");
    }

    private static void livesInBrasil() {
        // Implement country validation if needed
    }

    private static void hasMinimumAge(LocalDate birthDate) {
        if (birthDate != null && birthDate.isAfter(LocalDate.now().minusYears(18))) {
            throw new IllegalArgumentException("Person must be at least 18 years old");
        }
    }
}
