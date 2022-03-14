package com.lz.hexagonal.arch.domain.person.usecases.impl;

import com.lz.hexagonal.arch.domain.person.models.Person;
import com.lz.hexagonal.arch.domain.person.ports.out.ICreatePersonPort;
import com.lz.hexagonal.arch.domain.person.ports.commands.CreatedPersonPortCommand;
import com.lz.hexagonal.arch.domain.person.usecases.ICreatePersonUseCase;
import com.lz.hexagonal.arch.domain.person.usecases.commands.CreatePersonCommand;

public record CreatePersonUseCase(ICreatePersonPort persistenceAdapter) implements ICreatePersonUseCase {

    @Override
    public Person execute(final CreatePersonCommand createPersonCommand) {
        CreatedPersonPortCommand responsePort = persistenceAdapter.execute(createPersonCommand.getPerson());
        return responsePort.toPerson();
    }
}
