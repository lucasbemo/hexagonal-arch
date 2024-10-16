package com.lz.hexagonal.arch.domain.person.ports.commands;

public record CreatedPersonPortCommand (long id, String name, String email) {}
