package com.lz.hexagonal.arch.domain.person.usecases.impl;

import com.lz.hexagonal.arch.domain.person.models.Person;
import com.lz.hexagonal.arch.domain.person.ports.IFindPersonPort;
import com.lz.hexagonal.arch.domain.person.usecases.IFindPersonUseCase;
import lombok.extern.slf4j.Slf4j;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Slf4j
public record FindPersonUseCase(IFindPersonPort findPersonPort) implements IFindPersonUseCase {

    @Override
    public Person execute(final long id) {
        log.info("person_finding", kv("id", id));
        Person person = findPersonPort.execute(id);
        log.info("person_found", kv("id", id), kv("person", person));

        return person;
    }

    @Override
    public Person executeFindByEmail(final String email) {
        log.info("person_finding", kv("email", email));
        Person person = findPersonPort.executeFindByEmail(email);
        log.info("person_found", kv("email", email), kv("person", person));

        return person;
    }
}
