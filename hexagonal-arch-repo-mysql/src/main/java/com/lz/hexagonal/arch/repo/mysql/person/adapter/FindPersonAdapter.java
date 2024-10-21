package com.lz.hexagonal.arch.repo.mysql.person.adapter;

import com.lz.hexagonal.arch.repo.mysql.person.infra.mappers.PersonMapper;
import com.lz.hexagonal.arch.repo.mysql.person.persistence.entities.PersonEntity;
import com.lz.hexagonal.arch.repo.mysql.person.persistence.repository.IPersonRepository;
import com.lz.hexagonal.arch.domain.person.models.Person;
import com.lz.hexagonal.arch.domain.person.ports.IFindPersonPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public record FindPersonAdapter(IPersonRepository repository, PersonMapper personMapper) implements IFindPersonPort {
    static final Logger logger = LoggerFactory.getLogger(FindPersonAdapter.class);

    @Override
    public Person execute(final long id) {
        logger.info("person_finding {}", id);

        PersonEntity entity = repository.findById(id).orElseThrow();
        Person person = personMapper.toPerson(entity);

        logger.info("person_found {}  {}", id, person);
        return person;
    }

    @Override
    public Person executeFindByEmail(final String email) {
        logger.info("person_findingByEmail {}", email);

        PersonEntity entity = repository.findByEmail(email).orElseThrow();
        Person person = personMapper.toPerson(entity);

        logger.info("person_foundByEmail {} {}", email, person);
        return person;
    }
}
