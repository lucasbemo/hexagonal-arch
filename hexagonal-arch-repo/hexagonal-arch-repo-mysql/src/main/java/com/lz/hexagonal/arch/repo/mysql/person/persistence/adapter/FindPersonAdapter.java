package com.lz.hexagonal.arch.repo.mysql.person.persistence.adapter;

import com.lz.hexagonal.arch.domain.person.models.Person;
import com.lz.hexagonal.arch.domain.person.ports.IFindPersonPort;
import com.lz.hexagonal.arch.repo.mysql.person.mappers.PersonMapper;
import com.lz.hexagonal.arch.repo.mysql.person.persistence.entities.PersonEntity;
import com.lz.hexagonal.arch.repo.mysql.person.persistence.repository.IPersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Slf4j
@Component
public record FindPersonAdapter(IPersonRepository repository, PersonMapper personMapper) implements IFindPersonPort {

    @Override
    public Person execute(final long id) {
        log.info("person_finding", kv("id", id));

        PersonEntity entity = repository.findById(id).orElseThrow();
        Person person = personMapper.toPerson(entity);

        log.info("person_found", kv("id", id), kv("person", person));
        return person;
    }

    @Override
    public Person executeFindByEmail(final String email) {
        log.info("person_finding", kv("email", email));

        PersonEntity entity = repository.findByEmail(email).orElseThrow();
        Person person = personMapper.toPerson(entity);

        log.info("person_found", kv("email", email), kv("person", person));
        return person;
    }
}
