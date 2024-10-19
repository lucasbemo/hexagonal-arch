package com.lz.hexagonal.arch.application.infra.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lz.hexagonal.arch.domain.infra.ErrorCodes;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ErrorDTO (
    int status,
    ErrorCodes error,
    String message,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    LocalDateTime timestamp,
    String path) {

    public static ErrorDTO from(
            final HttpStatus httpStatus, final ErrorCodes oSErrorCode, final String message, final String path) {
        return new ErrorDTO(httpStatus.value(), oSErrorCode, message, LocalDateTime.now(), path);
    }
}
