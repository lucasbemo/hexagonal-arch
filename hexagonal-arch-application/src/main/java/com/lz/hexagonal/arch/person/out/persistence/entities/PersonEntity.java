package com.lz.hexagonal.arch.person.out.persistence.entities;

import com.lz.hexagonal.arch.domain.person.models.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class PersonEntity {

    @Id @GeneratedValue
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

    public static PersonEntity fromPerson(final Person person) {
        return PersonEntity.builder()
                .id(person.id())
                .name(person.name())
                .email(person.email())
                .cpf(person.cpf())
                .phone(person.phone())
                .birthDate(person.birthDate())
                .createAt(person.createAt())
                .build();
    }

    public static PersonEntity fromPersonWithoutId(final Person person) {
        return PersonEntity.builder()
                .name(person.name())
                .email(person.email())
                .cpf(person.cpf())
                .phone(person.phone())
                .birthDate(person.birthDate())
                .createAt(person.createAt())
                .build();
    }

    public Person toPerson() {
        return new Person(id, name, email, cpf, phone, birthDate, createAt);
    }
}
