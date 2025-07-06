package com.lz.hexagonal.arch.application.person.usecases.impl;

import com.lz.hexagonal.arch.application.person.usecases.IListPersonUseCase;
import com.lz.hexagonal.arch.domain.person.dtos.ListPageablePersonDTO;
import com.lz.hexagonal.arch.domain.person.dtos.ListPersonDTO;
import com.lz.hexagonal.arch.domain.person.ports.IListPersonPort;
import org.springframework.stereotype.Service;

@Service
public record ListPersonUseCase(IListPersonPort persistenceAdapter) implements IListPersonUseCase {

    @Override
    public ListPageablePersonDTO execute(final ListPersonDTO listPersonDTO) {
        return persistenceAdapter.execute(listPersonDTO);
    }
}
