package com.lz.hexagonal.arch.domain.person.usecases.impl;

import com.lz.hexagonal.arch.domain.person.ports.out.IListPersonPort;
import com.lz.hexagonal.arch.domain.person.usecases.IListPersonUseCase;
import com.lz.hexagonal.arch.domain.person.usecases.commands.ListPersonCommand;
import com.lz.hexagonal.arch.domain.person.usecases.response.ListPageablePersonResponse;

public record ListPersonUseCase(IListPersonPort persistenceAdapter) implements IListPersonUseCase {

    @Override
    public ListPageablePersonResponse execute(final ListPersonCommand listPersonCommand) {
        return persistenceAdapter.execute(listPersonCommand);
    }
}
