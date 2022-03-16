package com.lz.hexagonal.arch.domain.person.usecases;

import com.lz.hexagonal.arch.domain.person.usecases.commands.ListPersonCommand;
import com.lz.hexagonal.arch.domain.person.usecases.response.ListPageablePersonResponse;

public interface IListPersonUseCase {
    ListPageablePersonResponse execute(final ListPersonCommand listPersonCommand);
}
