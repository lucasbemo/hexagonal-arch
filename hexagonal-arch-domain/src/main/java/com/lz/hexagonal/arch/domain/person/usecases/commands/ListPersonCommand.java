package com.lz.hexagonal.arch.domain.person.usecases.commands;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import java.util.Map;

@Value
@Builder
@EqualsAndHashCode(callSuper=false)
public class ListPersonCommand {
    @NotBlank
    private int page;
    @NotBlank
    private int size;
    @NotBlank
    private String sort;
    private Map<String, String> filters;
}
