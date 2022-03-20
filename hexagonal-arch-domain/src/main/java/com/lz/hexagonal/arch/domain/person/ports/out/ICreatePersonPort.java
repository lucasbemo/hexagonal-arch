package com.lz.hexagonal.arch.domain.person.ports.out;

import com.lz.hexagonal.arch.domain.person.models.Person;

public interface ICreatePersonPort {
    Person execute(final Person person);
}
