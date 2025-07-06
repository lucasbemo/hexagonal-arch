package com.lz.hexagonal.arch.domain.person.ports;

import com.lz.hexagonal.arch.domain.person.dtos.ListPageablePersonDTO;
import com.lz.hexagonal.arch.domain.person.dtos.ListPersonDTO;

public interface IListPersonPort {
    ListPageablePersonDTO execute(final ListPersonDTO listPersonDTO);
}
