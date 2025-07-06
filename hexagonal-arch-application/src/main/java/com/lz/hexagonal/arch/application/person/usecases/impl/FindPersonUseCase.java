package com.lz.hexagonal.arch.application.person.usecases.impl;

import com.lz.hexagonal.arch.application.person.usecases.IFindPersonUseCase;
import com.lz.hexagonal.arch.domain.person.models.Person;
import com.lz.hexagonal.arch.domain.person.ports.IFindPersonPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public record FindPersonUseCase(IFindPersonPort findPersonPort) implements IFindPersonUseCase {

    static final Logger logger = LoggerFactory.getLogger(FindPersonUseCase.class);

    @Override
    public Person execute(final long id) {
        Person person = findPersonPort.execute(id);
        return person;
    }

    @Override
    public Person executeFindByEmail(final String email) {
        Person person = findPersonPort.executeFindByEmail(email);
        return person;
    }
}
