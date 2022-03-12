package com.lz.hexagonal.arch;

import com.lz.hexagonal.arch.dto.ErrorDTO;
import com.lz.hexagonal.arch.dto.FieldErrorDTO;
import com.lz.hexagonal.arch.dto.OSErrorCodes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
@Slf4j
public class RestControllerAdvicer {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(BAD_REQUEST)
    public ErrorDTO handlerMethodArgumentNotValidException(final MethodArgumentNotValidException ex) {
        List<FieldErrorDTO> fieldErrorDTO = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldErrorDTO::from)
                .collect(Collectors.toList());

        ErrorDTO errorDto = ErrorDTO
                .from(BAD_REQUEST, OSErrorCodes.OS_ERROR_INVALID_ARGUMENTS,"Invalid Arguments", fieldErrorDTO);

        return errorDto;
    }
}
