package com.lz.hexagonal.arch.domain.person.ports.out;

import com.lz.hexagonal.arch.domain.person.usecases.commands.ListPersonCommand;
import com.lz.hexagonal.arch.domain.person.usecases.impl.ListPaginablePersonResponse;

public interface IListPersonPort {
    ListPaginablePersonResponse execute(final ListPersonCommand listPersonCommand);
}
