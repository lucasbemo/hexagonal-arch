package com.lz.hexagonal.arch.domain.person.usecase.commands;

import com.lz.hexagonal.arch.domain.commons.SelfValidating;
import com.lz.hexagonal.arch.domain.person.models.Person;
import jakarta.validation.constraints.NotNull;

public class CreatePersonCommand extends SelfValidating<CreatePersonCommand> {

    @NotNull
    Person person;

    public CreatePersonCommand(final Person person) {
        this.person = person;
        validateSelf();
    }
}
