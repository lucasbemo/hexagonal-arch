package com.lz.hexagonal.arch.person.adaptar.output;

import com.lz.hexagonal.arch.domain.person.models.Person;
import com.lz.hexagonal.arch.domain.person.ports.ICreatePersonPort;

public class CreatePersonRepositoryAdapter implements ICreatePersonPort {

    @Override
    public Person execute(Person person) {
        return null;
    }
}
