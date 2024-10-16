package com.lz.hexagonal.arch.application.infra.rest.dto;

import jakarta.validation.ConstraintViolation;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record FieldErrorDTO (
    String field,
    String message) {

    public static List<FieldErrorDTO> from(final List<FieldError> fieldErrors) {
        return fieldErrors
                .stream()
                .map(FieldErrorDTO::from)
                .collect(Collectors.toList());
    }

    public static FieldErrorDTO from(final FieldError fieldError) {
        return new FieldErrorDTO(fieldError.getField(), fieldError.getDefaultMessage());
    }

    public static List<FieldErrorDTO> from(Set<ConstraintViolation<?>> constraintViolations) {
        return null;
    }

    public static FieldErrorDTO from(ConstraintViolation<?> constraintViolation) {
        return null;
    }
}
