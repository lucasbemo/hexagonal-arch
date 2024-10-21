package com.lz.hexagonal.arch.repo.mysql.person.infra.mappers;

import com.lz.hexagonal.arch.repo.mysql.person.persistence.entities.PersonEntity;
import com.lz.hexagonal.arch.domain.person.models.Person;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    List<Person> toPerson(final List<PersonEntity> entities);

    Person toPerson(final PersonEntity entity);
}
