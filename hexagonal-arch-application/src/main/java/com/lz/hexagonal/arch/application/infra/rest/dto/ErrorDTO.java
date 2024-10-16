package com.lz.hexagonal.arch.application.infra.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lz.hexagonal.arch.domain.infra.ErrorCodes;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ErrorDTO (
    int code,
    ErrorCodes error,
    String message,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    LocalDateTime timestamp) {

    public static ErrorDTO from(final HttpStatus httpStatus, final ErrorCodes oSErrorCode, final String message) {
        return new ErrorDTO(httpStatus.value(), oSErrorCode, message, LocalDateTime.now());
    }
}
