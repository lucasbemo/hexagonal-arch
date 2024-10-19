package com.lz.hexagonal.arch.domain.person.models;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Person {
    private Long id;
    private String name;
    private String email;
    private String cpf;
    private String phone;
    private LocalDate birthDate;
    private LocalDateTime createAt;

    static final Logger logger = LoggerFactory.getLogger(Person.class.getClassLoader().getName());

    public Person(
            Long id, @NotBlank String name, @NotBlank String email, @Valid  @NotBlank @CPF String cpf, @NotBlank String phone,
            @NotNull LocalDate birthDate, LocalDateTime createAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.phone = phone;
        this.birthDate = birthDate;
        this.createAt = createAt;

        logger.info("creating a Person.");
        executeRules();
        logger.info("Person created succeed.");
    }

    private void executeRules() {
        hasMinimumAge();
        livesInBrasil();
    }

    private void livesInBrasil() {
    }

    private void hasMinimumAge() {
        if (birthDate.isAfter(LocalDate.now().minusYears(18))) {
            throw new IllegalArgumentException("Person must be at least 18 years old");
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", cpf='" + cpf + '\'' +
                ", phone='" + phone + '\'' +
                ", birthDate=" + birthDate +
                ", createAt=" + createAt +
                '}';
    }
}
