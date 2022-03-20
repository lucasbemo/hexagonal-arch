package com.lz.hexagonal.arch.domain.person.ports.commands;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreatedPersonPortCommand {
    private long id;
    private String name;
    private String email;

}
