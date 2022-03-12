package com.lz.hexagonal.arch.domain.person.ports;

import com.lz.hexagonal.arch.domain.person.models.Person;

public interface ICreatePersonPort {
    Person execute(final Person person);
}
