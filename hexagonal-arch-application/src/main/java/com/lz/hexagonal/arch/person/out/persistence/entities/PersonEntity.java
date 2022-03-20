package com.lz.hexagonal.arch.person.out.persistence.entities;

import com.lz.hexagonal.arch.domain.person.models.Person;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Builder
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class PersonEntity {

    @Id @GeneratedValue
    private long id;
    private String name;
    private String email;
    private int yearBirth;
    private LocalDateTime createAt;

    public static PersonEntity fromPerson(Person person) {
        return PersonEntity.builder()
                .id(person.getId())
                .name(person.getName())
                .email(person.getEmail())
                .yearBirth(person.getYearBirth())
                .createAt(person.getCreateAt())
                .build();
    }

    public static PersonEntity fromPersonWithoutId(Person person) {
        return PersonEntity.builder()
                .name(person.getName())
                .email(person.getEmail())
                .yearBirth(person.getYearBirth())
                .createAt(person.getCreateAt())
                .build();
    }

    public Person toPerson(PersonEntity save) {
        return new Person(id, name, email, yearBirth, createAt);
    }
}
