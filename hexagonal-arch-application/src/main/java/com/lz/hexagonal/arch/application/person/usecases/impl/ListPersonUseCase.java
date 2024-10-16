package com.lz.hexagonal.arch.application.person.usecases.impl;

import com.lz.hexagonal.arch.application.person.usecases.IListPersonUseCase;
import com.lz.hexagonal.arch.domain.person.ports.out.IListPersonPort;
import com.lz.hexagonal.arch.domain.person.usecase.commands.ListPersonCommand;
import com.lz.hexagonal.arch.domain.person.usecase.response.ListPageablePersonResponse;
import org.springframework.stereotype.Service;

@Service
public record ListPersonUseCase(IListPersonPort persistenceAdapter) implements IListPersonUseCase {

    @Override
    public ListPageablePersonResponse execute(final ListPersonCommand listPersonCommand) {
        return persistenceAdapter.execute(listPersonCommand);
    }
}
