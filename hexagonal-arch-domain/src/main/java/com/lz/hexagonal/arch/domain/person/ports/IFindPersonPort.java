package com.lz.hexagonal.arch.domain.person.ports;

import com.lz.hexagonal.arch.domain.person.models.Person;

public interface IFindPersonPort {
    Person execute(final long id);
    Person executeFindByEmail(final String email);
}
