package com.lz.hexagonal.arch.application.person.usecases;

import com.lz.hexagonal.arch.domain.infra.exceptions.HexagonalException;
import com.lz.hexagonal.arch.domain.person.models.Person;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
public interface ICreatePersonUseCase {
    Person execute(@Valid final Person person) throws HexagonalException;
}

