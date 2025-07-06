package com.lz.hexagonal.arch.repo.mysql.person.persistence.entities;

import com.lz.hexagonal.arch.domain.person.models.Person;
import com.lz.hexagonal.arch.repo.mysql.person.persistence.uuidv7.GeneratedUuidV7;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class PersonEntity {

    @Id
    @GeneratedUuidV7
    private UUID id;

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

    @NotNull
    @Column(nullable = false)
    private LocalDateTime updateAt;

    public PersonEntity() {
    }

    public PersonEntity(
            UUID id, String name, String email, String cpf, String phone, LocalDate birthDate, LocalDateTime createAt) {
        if (id == null)
            this.id = id;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.phone = phone;
        this.birthDate = birthDate;
        if (this.createAt == null)
            this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public static PersonEntity fromPerson(final Person person) {
        return new PersonEntity(
                (person.id() == null? null: person.id()), person.name(), person.email(), person.cpf(),
                person.phone(), person.birthDate(), person.createAt());
    }

    public Person toPerson() {
        return new Person(id, name, email, cpf, phone, birthDate, createAt);
    }

    @Override
    public String toString() {
        return "PersonEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", cpf='" + cpf + '\'' +
                ", phone='" + phone + '\'' +
                ", birthDate=" + birthDate +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
