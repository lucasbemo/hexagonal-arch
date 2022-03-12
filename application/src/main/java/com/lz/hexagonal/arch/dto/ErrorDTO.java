package com.lz.hexagonal.arch.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDTO {
    private int code;
    private OSErrorCodes error;
    private String messagem;
    private List<FieldErrorDTO> fieldsErrors;

    public static ErrorDTO from(final HttpStatus httpStatus, final OSErrorCodes oSErrorCode, final String message, final List<FieldErrorDTO> fieldErrorDTO) {
        return ErrorDTO.builder()
                .code(httpStatus.value())
                .error(oSErrorCode)
                .messagem(message)
                .fieldsErrors(fieldErrorDTO)
                .build();
    }
}
