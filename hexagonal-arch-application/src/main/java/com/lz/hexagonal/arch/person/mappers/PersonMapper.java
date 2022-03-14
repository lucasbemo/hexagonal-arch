package com.lz.hexagonal.arch.person.mappers;

import com.lz.hexagonal.arch.domain.person.models.Person;
import com.lz.hexagonal.arch.domain.person.ports.commands.CreatedPersonPortCommand;
import com.lz.hexagonal.arch.person.in.web.adapter.dtos.CreatePersonRequestWeb;
import com.lz.hexagonal.arch.person.out.persistence.entities.PersonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface PersonMapper {
    @Mapping(target = "id", ignore = true)
    Person toPerson(final CreatePersonRequestWeb createPersonRequestWeb);

    CreatedPersonPortCommand toCreatedPersonResponsePort(final PersonEntity entity);

    List<Person> toPerson(final List<PersonEntity> entities);


}
