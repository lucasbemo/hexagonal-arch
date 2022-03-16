package com.lz.hexagonal.arch.domain.person.usecases.commands;

import javax.validation.constraints.NotBlank;
import java.util.Map;

public record ListPersonCommand(@NotBlank int page, @NotBlank int size, String sort,
                                Map<String, String> filters) {
}
