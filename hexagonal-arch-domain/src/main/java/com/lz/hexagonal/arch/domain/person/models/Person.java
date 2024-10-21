package com.lz.hexagonal.arch.domain.person.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Person {
    private Long id;
    @NotBlank(message = "name must not be null or blank")
    private String name;
    @NotBlank(message = "email must not be null or blank")
    private String email;
    @CPF(message = "cpf must not be null or blank")
    @NotBlank(message = "cpf must not be null or blank")
    private String cpf;
    @NotBlank(message = "phone must not be null or blank")
    private String phone;
    @NotNull(message = "birthDate must not be null or blank")
    private LocalDate birthDate;
    private LocalDateTime createAt;

    static final Logger logger = LoggerFactory.getLogger(Person.class);

    public Person(
            Long id, String name, String email, String cpf, String phone, LocalDate birthDate, LocalDateTime createAt) {
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
        hasRequiredFields();
        hasMinimumAge();
        livesInBrasil();
    }

    private void hasRequiredFields() {
        if (name == null || name.isEmpty())
            logger.info("Name is required");
//            throw new IllegalArgumentException("Name is required");
    }

    private void livesInBrasil() {
    }

    private void hasMinimumAge() {
        if (birthDate != null && birthDate.isAfter(LocalDate.now().minusYears(18))) {
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
