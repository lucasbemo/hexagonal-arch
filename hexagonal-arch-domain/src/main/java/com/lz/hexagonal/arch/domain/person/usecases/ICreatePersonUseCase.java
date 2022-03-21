package com.lz.hexagonal.arch.domain.person.usecases;

import com.lz.hexagonal.arch.domain.infra.HexagonalNotFoundException;
import com.lz.hexagonal.arch.domain.person.models.Person;

public interface ICreatePersonUseCase {
    Person execute(final Person person) throws HexagonalNotFoundException;
}

