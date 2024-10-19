package com.lz.hexagonal.arch.application.person.usecases;

import com.lz.hexagonal.arch.domain.infra.exceptions.HexagonalException;
import com.lz.hexagonal.arch.domain.infra.exceptions.HexagonalNotFoundException;
import com.lz.hexagonal.arch.domain.person.models.Person;

public interface ICreatePersonUseCase {
    Person execute(final Person person) throws HexagonalException;
}

