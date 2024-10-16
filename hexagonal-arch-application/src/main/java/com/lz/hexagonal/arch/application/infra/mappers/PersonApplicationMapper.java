package com.lz.hexagonal.arch.application.infra.mappers;

import com.lz.hexagonal.arch.application.person.adapters.in.web.dtos.CreatePersonRequestWeb;
import com.lz.hexagonal.arch.domain.person.models.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PersonApplicationMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createAt", ignore = true)
    Person toPerson(final CreatePersonRequestWeb createPersonRequestWeb);
}
