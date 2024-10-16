package com.lz.hexagonal.arch.person.usecases;

import com.lz.hexagonal.arch.domain.person.usecase.commands.ListPersonCommand;
import com.lz.hexagonal.arch.domain.person.usecase.response.ListPageablePersonResponse;

public interface IListPersonUseCase {
    ListPageablePersonResponse execute(final ListPersonCommand listPersonCommand);
}
