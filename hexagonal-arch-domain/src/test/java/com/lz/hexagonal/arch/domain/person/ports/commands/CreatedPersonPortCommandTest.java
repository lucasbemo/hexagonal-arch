package com.lz.hexagonal.arch.domain.person.ports.commands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreatedPersonPortCommandTest {

    @Test
    public void when_createCreatedPersonPortCommandTest_expect_inputValuesIsSameAttributes() {
        var createdPersonPortCommand = new CreatedPersonPortCommand(
                1L, "Fulano", "fulano@email.com");

        Assertions.assertEquals(createdPersonPortCommand.getId(), 1L);
        Assertions.assertEquals(createdPersonPortCommand.getName(), "Fulano");
        Assertions.assertEquals(createdPersonPortCommand.getEmail(), "fulano@email.com");
    }
}
