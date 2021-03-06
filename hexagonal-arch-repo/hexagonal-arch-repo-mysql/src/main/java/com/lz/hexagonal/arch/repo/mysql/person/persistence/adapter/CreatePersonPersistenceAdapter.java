package com.lz.hexagonal.arch.repo.mysql.person.persistence.adapter;

import com.lz.hexagonal.arch.domain.person.models.Person;
import com.lz.hexagonal.arch.domain.person.ports.out.ICreatePersonPort;
import com.lz.hexagonal.arch.repo.mysql.person.persistence.repository.IPersonRepository;
import org.springframework.stereotype.Service;
import com.lz.hexagonal.arch.repo.mysql.person.persistence.entities.PersonEntity;

@Service
public record CreatePersonPersistenceAdapter(IPersonRepository repository) implements ICreatePersonPort {

    @Override
    public Person execute(final Person person) {
        PersonEntity entity = PersonEntity.fromPersonWithoutId(person);

        return repository.save(entity).toPerson();
    }
}
