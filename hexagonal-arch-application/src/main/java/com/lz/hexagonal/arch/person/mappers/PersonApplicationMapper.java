package com.lz.hexagonal.arch.person.mappers;

import com.lz.hexagonal.arch.domain.person.models.Person;
import com.lz.hexagonal.arch.person.in.web.adapters.dtos.CreatePersonRequestWeb;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PersonApplicationMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createAt", ignore = true)
    Person toPerson(final CreatePersonRequestWeb createPersonRequestWeb);
}
