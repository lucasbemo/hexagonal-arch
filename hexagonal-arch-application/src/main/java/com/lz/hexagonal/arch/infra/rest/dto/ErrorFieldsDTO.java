package com.lz.hexagonal.arch.infra.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lz.hexagonal.arch.domain.infra.ErrorCodes;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorFieldsDTO (
    int code,
    ErrorCodes error,
    String message,
    List<FieldErrorDTO> fieldsErrors,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    LocalDateTime timestamp) {

    public static ErrorFieldsDTO from(
            final HttpStatus httpStatus, final ErrorCodes oSErrorCode
            , final String message, final List<FieldErrorDTO> fieldErrorDTO) {
        return new ErrorFieldsDTO(httpStatus.value(), oSErrorCode, message, fieldErrorDTO, LocalDateTime.now());
    }
}
