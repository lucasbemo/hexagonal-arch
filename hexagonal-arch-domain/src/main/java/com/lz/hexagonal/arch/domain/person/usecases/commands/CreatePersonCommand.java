package com.lz.hexagonal.arch.domain.person.usecases.commands;

import com.lz.hexagonal.arch.domain.commons.SelfValidating;
import com.lz.hexagonal.arch.domain.person.models.Person;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
@Builder
@EqualsAndHashCode(callSuper=false)
public class CreatePersonCommand extends SelfValidating<CreatePersonCommand> {

    @NotNull
    Person person;

    public CreatePersonCommand(final Person person) {
        this.person = person;
        validateSelf();
    }
}
