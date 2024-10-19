package com.lz.hexagonal.arch.domain.person.dtos;

import jakarta.validation.constraints.NotBlank;

import java.util.Map;

public record ListPersonDTO(
        @NotBlank int page,
        @NotBlank int size,
        String sort,
        String name,
        String email) {

    private static Map<String, String> filters;

    public ListPersonDTO {
        getListPageableFilters(name, email);
    }

    private void getListPageableFilters(final String name, final String email) {
        if (name != null) filters.put("name", name);
        if (email != null) filters.put("email", email);
    }

    public Map<String, String> getFilters() {
        return filters;
    }
}
