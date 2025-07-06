package com.lz.hexagonal.arch.application.person.usecases.impl;

import com.lz.hexagonal.arch.application.person.usecases.ICreatePersonUseCase;
import com.lz.hexagonal.arch.domain.infra.exceptions.HexagonalException;
import com.lz.hexagonal.arch.domain.person.models.Person;
import com.lz.hexagonal.arch.domain.person.ports.ICreatePersonPort;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreatePersonUseCase implements ICreatePersonUseCase {
    static final Logger logger = LoggerFactory.getLogger(CreatePersonUseCase.class);
    private final ICreatePersonPort createAdapter;

    @Autowired
    public CreatePersonUseCase(final ICreatePersonPort createAdapter) {
        this.createAdapter = createAdapter;
    }

    @Override
    public Person execute(@Valid final Person person) throws HexagonalException {
        return createAdapter.execute(person);
    }
}
