package com.lz.hexagonal.arch.domain.person.dtos;

import com.lz.hexagonal.arch.domain.commons.SelfValidating;
import com.lz.hexagonal.arch.domain.person.models.Person;
import jakarta.validation.constraints.NotNull;

public class CreatePersonDTO extends SelfValidating<CreatePersonDTO> {

    @NotNull
    Person person;

    public CreatePersonDTO(final Person person) {
        this.person = person;
        validateSelf();
    }
}
