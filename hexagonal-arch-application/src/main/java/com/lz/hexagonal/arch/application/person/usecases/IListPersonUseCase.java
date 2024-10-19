package com.lz.hexagonal.arch.application.person.usecases;

import com.lz.hexagonal.arch.domain.person.dtos.ListPersonDTO;
import com.lz.hexagonal.arch.domain.person.dtos.ListPageablePersonDTO;

public interface IListPersonUseCase {
    ListPageablePersonDTO execute(final ListPersonDTO listPersonDTO);
}
