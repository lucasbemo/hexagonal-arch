package com.lz.hexagonal.arch.person.usecases.impl;

import com.lz.hexagonal.arch.domain.infra.HexagonalNotFoundException;
import com.lz.hexagonal.arch.domain.person.models.Person;
import com.lz.hexagonal.arch.domain.person.ports.out.ICreatePersonPort;
import com.lz.hexagonal.arch.person.usecases.ICreatePersonUseCase;
import com.lz.hexagonal.arch.person.usecases.IFindPersonUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.NoSuchElementException;

public record CreatePersonUseCase(
        ICreatePersonPort createAdapter, IFindPersonUseCase findPersonUseCase) implements ICreatePersonUseCase {

    static final Logger logger = LoggerFactory.getLogger(CreatePersonUseCase.class.getClassLoader().getName());

    @Override
    public Person execute(final Person person) throws HexagonalNotFoundException {
        logger.info("person_creating", person);
        Person personDB = null;

        try {
            personDB = findPersonUseCase.executeFindByEmail(person.email());
        } catch (NoSuchElementException exception) {
            logger.trace("person_creating", "isTherePerson");
        }

        if (personDB != null)
            throw new HexagonalNotFoundException("person already exists with this email.");

        Person personCreated = createAdapter.execute(person);

        logger.info("person_created", personCreated);
        return personCreated;
    }
}
