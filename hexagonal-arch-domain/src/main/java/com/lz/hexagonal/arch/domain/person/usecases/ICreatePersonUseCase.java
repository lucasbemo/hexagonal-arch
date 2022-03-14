package com.lz.hexagonal.arch.domain.person.usecases;

import com.lz.hexagonal.arch.domain.person.models.Person;
import com.lz.hexagonal.arch.domain.person.usecases.commands.CreatePersonCommand;

public interface ICreatePersonUseCase {
    Person execute(final CreatePersonCommand createPersonCommand);
}

