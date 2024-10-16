package com.lz.hexagonal.arch.repo.mysql.person.persistence.entities;

import com.lz.hexagonal.arch.domain.person.models.Person;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
public class PersonEntity {

    @Id
    @GeneratedValue
    private long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank
    @CPF
    @Column(unique = true, nullable = false)
    private String cpf;

    @NotBlank
    @Column(nullable = false)
    private String phone;

    @NotNull
    @Column(nullable = false)
    private LocalDate birthDate;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime createAt;

    public PersonEntity() {
    }

    public PersonEntity(
            long id, String name, String email, String cpf, String phone, LocalDate birthDate, LocalDateTime createAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.phone = phone;
        this.birthDate = birthDate;
        this.createAt = createAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public static PersonEntity fromPerson(final Person person) {
        return new PersonEntity(
                person.id(), person.name(), person.email(), person.cpf(),
                person.phone(), person.birthDate(), person.createAt());
    }

    public static PersonEntity fromPersonWithoutId(final Person person) {
        PersonEntity entity = new PersonEntity();
        entity.setBirthDate(person.birthDate());
        entity.setCreateAt(person.createAt());
        entity.setEmail(person.email());
        entity.setName(person.name());
        entity.setPhone(person.phone());
        entity.setCpf(person.cpf());
        entity.createAt = LocalDateTime.now();
        return entity;
    }

    public Person toPerson() {
        return new Person(id, name, email, cpf, phone, birthDate, createAt);
    }
}
