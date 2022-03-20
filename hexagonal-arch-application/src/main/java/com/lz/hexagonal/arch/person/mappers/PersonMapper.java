package com.lz.hexagonal.arch.person.mappers;

import com.lz.hexagonal.arch.domain.person.models.Person;
import com.lz.hexagonal.arch.person.in.web.adapters.dtos.CreatePersonRequestWeb;
import com.lz.hexagonal.arch.person.out.persistence.entities.PersonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createAt", ignore = true)
    Person toPerson(final CreatePersonRequestWeb createPersonRequestWeb);

    List<Person> toPerson(final List<PersonEntity> entities);

    Person toPerson(final PersonEntity entity);
}
