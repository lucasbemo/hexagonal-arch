package com.lz.hexagonal.arch.domain.person.ports.out;

import com.lz.hexagonal.arch.domain.person.usecases.commands.ListPersonCommand;
import com.lz.hexagonal.arch.domain.person.usecases.response.ListPageablePersonResponse;

public interface IListPersonPort {
    ListPageablePersonResponse execute(final ListPersonCommand listPersonCommand);
}
