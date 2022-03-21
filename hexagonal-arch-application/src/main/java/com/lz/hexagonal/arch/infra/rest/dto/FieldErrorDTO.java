package com.lz.hexagonal.arch.infra.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.FieldError;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FieldErrorDTO {
    private String field;
    private String message;

    public static List<FieldErrorDTO> from(final List<FieldError> fieldErrors) {
        return fieldErrors
                .stream()
                .map(FieldErrorDTO::from)
                .collect(Collectors.toList());
    }

    public static FieldErrorDTO from(final FieldError fieldError) {
        return FieldErrorDTO.builder()
                .field(fieldError.getField())
                .message(fieldError.getDefaultMessage())
                .build();
    }

    public static List<FieldErrorDTO> from(Set<ConstraintViolation<?>> constraintViolations) {
        return null;
    }
}
