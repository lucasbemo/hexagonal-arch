package com.lz.hexagonal.arch.domain.person.ports.commands;

import com.lz.hexagonal.arch.domain.person.models.Person;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreatedPersonPortCommand {
    private long id;
    private String name;
    private String email;

    public Person toPerson() {
        return Person.builder()
                .id(id)
                .name(name)
                .email(email)
                .build();
    }
}
