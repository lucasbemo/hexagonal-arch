package com.lz.hexagonal.arch.application.person.usecases.impl;

import com.lz.hexagonal.arch.application.person.usecases.IFindPersonUseCase;
import com.lz.hexagonal.arch.domain.person.models.Person;
import com.lz.hexagonal.arch.domain.person.ports.IFindPersonPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public record FindPersonUseCase(IFindPersonPort findPersonPort) implements IFindPersonUseCase {

    static final Logger logger = LoggerFactory.getLogger(FindPersonUseCase.class.getClassLoader().getName());

    @Override
    public Person execute(final long id) {
        logger.info("person_finding", id);
        Person person = findPersonPort.execute(id);
        logger.info("person_found", id, person);

        return person;
    }

    @Override
    public Person executeFindByEmail(final String email) {
        logger.info("person_findingByEmail", email);
        Person person = findPersonPort.executeFindByEmail(email);
        logger.info("person_found", email, person);

        return person;
    }
}
