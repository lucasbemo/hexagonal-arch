package com.lz.hexagonal.arch.domain.person.ports;

import com.lz.hexagonal.arch.domain.infra.exceptions.HexagonalException;
import com.lz.hexagonal.arch.domain.person.models.Person;
import jakarta.validation.Valid;

public interface ICreatePersonPort {
    Person execute(@Valid final Person person) throws HexagonalException;
}
