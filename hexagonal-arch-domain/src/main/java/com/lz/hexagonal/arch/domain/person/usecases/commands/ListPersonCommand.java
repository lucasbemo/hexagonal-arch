package com.lz.hexagonal.arch.domain.person.usecases.commands;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.Map;

@Getter
@ToString
@EqualsAndHashCode
public class ListPersonCommand {
    private final Map<String, String> filters;
    private final int page;
    private final int size;
    private final String sort;

    public ListPersonCommand(@NotBlank final int page, @NotBlank final int size, final String sort,
                             String name, String email) {
        this.page = page;
        this.size = size;
        this.sort = sort;
        this.filters = new HashMap<>();
        getListPageableFilters(name, email);
    }

    private void getListPageableFilters(final String name, final String email) {
        if (name != null) filters.put("name", name);
        if (email != null) filters.put("email", email);
    }
}
