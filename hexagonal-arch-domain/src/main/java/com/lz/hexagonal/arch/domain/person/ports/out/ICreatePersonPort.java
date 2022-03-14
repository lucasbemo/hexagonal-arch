package com.lz.hexagonal.arch.domain.person.ports.out;

import com.lz.hexagonal.arch.domain.person.models.Person;
import com.lz.hexagonal.arch.domain.person.ports.commands.CreatedPersonPortCommand;

public interface ICreatePersonPort {
    CreatedPersonPortCommand execute(final Person person);
}
