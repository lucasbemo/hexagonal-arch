package com.lz.hexagonal.arch.domain.person.usecases;

import com.lz.hexagonal.arch.domain.person.models.Person;

public interface IFindPersonUseCase {
    Person execute(final long id);
    Person executeFindByEmail(final String email);
}
