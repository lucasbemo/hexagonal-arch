package com.lz.hexagonal.arch.person.in.web.adapter.dtos;

import com.lz.hexagonal.arch.domain.person.models.Person;
import com.lz.hexagonal.arch.person.mappers.PersonMapperImpl;
import lombok.*;
import org.mapstruct.Mapping;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@ToString
public class CreatePersonRequestWeb {
    @NotBlank
    private String name;
    @NotBlank
    private String email;

    public Person toPerson() {
        return new PersonMapperImpl().toPerson(this);
    }
}
