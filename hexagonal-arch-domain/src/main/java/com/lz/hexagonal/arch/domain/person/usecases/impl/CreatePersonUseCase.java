package com.lz.hexagonal.arch.domain.person.usecases.impl;

import com.lz.hexagonal.arch.domain.person.models.Person;
import com.lz.hexagonal.arch.domain.person.ports.out.ICreatePersonPort;
import com.lz.hexagonal.arch.domain.person.usecases.ICreatePersonUseCase;
import com.lz.hexagonal.arch.domain.person.usecases.IFindPersonUseCase;
import lombok.extern.slf4j.Slf4j;

import java.util.NoSuchElementException;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Slf4j
public record CreatePersonUseCase(
        ICreatePersonPort createAdapter, IFindPersonUseCase findPersonUseCase) implements ICreatePersonUseCase {

    @Override
    public Person execute(final Person person) {
        log.info("person_creating", kv("person", person));
        Person personDB = null;

        try {
            personDB = findPersonUseCase.executeFindByEmail(person.getEmail());
        } catch (NoSuchElementException exception) {
            log.trace("person_creating", kv("isTherePerson", false));
        }

        if (personDB != null)
            throw new IllegalArgumentException("person already exists with this email.");

        Person personCreated = createAdapter.execute(person);

        log.info("person_created", kv("personCreated", personCreated));
        return personCreated;
    }
}
