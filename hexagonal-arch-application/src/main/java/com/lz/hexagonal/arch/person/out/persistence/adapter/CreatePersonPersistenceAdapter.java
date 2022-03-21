package com.lz.hexagonal.arch.person.out.persistence.adapter;

import com.lz.hexagonal.arch.domain.person.models.Person;
import com.lz.hexagonal.arch.domain.person.ports.out.ICreatePersonPort;
import com.lz.hexagonal.arch.person.out.persistence.entities.PersonEntity;
import com.lz.hexagonal.arch.person.out.persistence.repository.IPersonRepository;
import org.springframework.stereotype.Service;

@Service
public record CreatePersonPersistenceAdapter(IPersonRepository repository) implements ICreatePersonPort {

    @Override
    public Person execute(final Person person) {
        PersonEntity entity = PersonEntity.fromPersonWithoutId(person);

        return repository.save(entity).toPerson();
    }
}
