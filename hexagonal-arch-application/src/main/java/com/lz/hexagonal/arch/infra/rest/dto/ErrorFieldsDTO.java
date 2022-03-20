package com.lz.hexagonal.arch.infra.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorFieldsDTO {
    private int code;
    private OSErrorCodes error;
    private String message;
    private List<FieldErrorDTO> fieldsErrors;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    public static ErrorFieldsDTO from(
            final HttpStatus httpStatus, final OSErrorCodes oSErrorCode
            , final String message, final List<FieldErrorDTO> fieldErrorDTO) {
        return ErrorFieldsDTO.builder()
                .code(httpStatus.value())
                .error(oSErrorCode)
                .message(message)
                .fieldsErrors(fieldErrorDTO)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
