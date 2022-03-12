package com.lz.hexagonal.arch.domain.person.usecases.impl;

import com.lz.hexagonal.arch.domain.person.models.Person;
import com.lz.hexagonal.arch.domain.person.ports.ICreatePersonPort;
import com.lz.hexagonal.arch.domain.person.usecases.ICreatePersonUseCase;

public class CreatePersonUseCase implements ICreatePersonUseCase {

    private ICreatePersonPort repository;

    public CreatePersonUseCase(final ICreatePersonPort repository) {
        this.repository = repository;
    }

    @Override
    public Person execute(final Person person) {

        return repository.execute(person);
    }
}
