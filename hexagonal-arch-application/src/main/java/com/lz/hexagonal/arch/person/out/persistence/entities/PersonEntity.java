package com.lz.hexagonal.arch.person.out.persistence.entities;

import com.lz.hexagonal.arch.domain.person.models.Person;
import com.lz.hexagonal.arch.domain.person.ports.commands.CreatedPersonPortCommand;
import com.lz.hexagonal.arch.person.mappers.PersonMapperImpl;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class PersonEntity {

    @Id @GeneratedValue
    private long id;
    private String name;
    private String email;

    public static PersonEntity fromPerson(Person person) {
        return PersonEntity.builder()
                .name(person.getName())
                .email(person.getEmail())
                .build();
    }

    public CreatedPersonPortCommand toCreatedPersonResponsePort(PersonEntity save) {
        return new PersonMapperImpl().toCreatedPersonResponsePort(this);
    }
}
