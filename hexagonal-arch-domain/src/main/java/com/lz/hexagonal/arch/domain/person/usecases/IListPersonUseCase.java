package com.lz.hexagonal.arch.domain.person.usecases;

import com.lz.hexagonal.arch.domain.person.models.Person;
import com.lz.hexagonal.arch.domain.person.usecases.commands.ListPersonCommand;
import com.lz.hexagonal.arch.domain.person.usecases.impl.ListPaginablePersonResponse;

import java.util.List;

public interface IListPersonUseCase {
    ListPaginablePersonResponse execute(final ListPersonCommand listPersonCommand);
}
