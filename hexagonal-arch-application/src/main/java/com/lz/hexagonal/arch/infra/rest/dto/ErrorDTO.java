package com.lz.hexagonal.arch.infra.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDTO {
    private int code;
    private OSErrorCodes error;
    private String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    public static ErrorDTO from(final HttpStatus httpStatus, final OSErrorCodes oSErrorCode, final String message) {
        return ErrorDTO.builder()
                .code(httpStatus.value())
                .error(oSErrorCode)
                .message(message)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
