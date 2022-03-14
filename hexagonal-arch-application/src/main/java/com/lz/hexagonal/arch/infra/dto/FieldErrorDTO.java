package com.lz.hexagonal.arch.infra.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.FieldError;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FieldErrorDTO {
    private String field;
    private String message;

    public static FieldErrorDTO from(FieldError fieldError) {
        return FieldErrorDTO.builder()
                .field(fieldError.getField())
                .message(fieldError.getDefaultMessage())
                .build();
    }
}
