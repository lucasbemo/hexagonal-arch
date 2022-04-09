package com.lz.hexagonal.arch.repo.mysql.person.mappers;

import com.lz.hexagonal.arch.domain.person.models.Person;
import com.lz.hexagonal.arch.repo.mysql.person.persistence.entities.PersonEntity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-09T13:56:45-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.1 (Eclipse Adoptium)"
)
@Component
public class PersonMapperImpl implements PersonMapper {

    @Override
    public List<Person> toPerson(List<PersonEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<Person> list = new ArrayList<Person>( entities.size() );
        for ( PersonEntity personEntity : entities ) {
            list.add( toPerson( personEntity ) );
        }

        return list;
    }

    @Override
    public Person toPerson(PersonEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String email = null;
        String cpf = null;
        String phone = null;
        LocalDate birthDate = null;
        LocalDateTime createAt = null;

        id = entity.getId();
        name = entity.getName();
        email = entity.getEmail();
        cpf = entity.getCpf();
        phone = entity.getPhone();
        birthDate = entity.getBirthDate();
        createAt = entity.getCreateAt();

        Person person = new Person( id, name, email, cpf, phone, birthDate, createAt );

        return person;
    }
}
