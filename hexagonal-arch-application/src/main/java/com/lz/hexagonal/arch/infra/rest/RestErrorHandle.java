package com.lz.hexagonal.arch.infra.rest;

import com.lz.hexagonal.arch.domain.infra.HexagonalNotFoundException;
import com.lz.hexagonal.arch.domain.infra.ErrorCodes;
import com.lz.hexagonal.arch.infra.rest.dto.ErrorDTO;
import com.lz.hexagonal.arch.infra.rest.dto.ErrorFieldsDTO;
import com.lz.hexagonal.arch.infra.rest.dto.FieldErrorDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestControllerAdvice
public class RestErrorHandle {

    /**
     * Handle HttpMessageNotReadableException. Happens when request JSON is malformed.
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<ErrorDTO> handleHttpMessageNotReadable(
            HttpMessageNotReadableException exception) {
        log.error("handleHttpMessageNotReadable", exception);

        var message = exception.getMessage();

        if (exception.getCause() != null
                && exception.getCause().getCause() != null
                && exception.getCause().getCause().getLocalizedMessage() != null)
            message = exception.getCause().getCause().getLocalizedMessage();

        return buildResponse(ErrorDTO
                        .from(BAD_REQUEST, ErrorCodes.OS_ERROR_INVALID_ARGUMENTS, message)
                , BAD_REQUEST);
    }

    /**
     * Handle MethodArgumentNotValidException. Exception from Java Bean Validation.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<ErrorFieldsDTO> handleMethodArgumentNotValidException(
            final MethodArgumentNotValidException exception) {
        log.error("handleMethodArgumentNotValidException", exception);
        List<FieldErrorDTO> fieldErrorDTO = FieldErrorDTO.from(exception.getBindingResult().getFieldErrors());

        return buildResponse(ErrorFieldsDTO.from(
                                BAD_REQUEST,
                                ErrorCodes.OS_ERROR_INVALID_ARGUMENTS,
                                "Invalid Arguments",
                                fieldErrorDTO)
                , BAD_REQUEST);
    }

    /**
     * Handle javax.validation.ConstraintViolationException. Exception from Java Bean Validation manually.
     */
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<ErrorFieldsDTO> handleConstraintViolation(ConstraintViolationException exception) {
        log.error("handleConstraintViolation", exception);
        List<FieldErrorDTO> fieldErrorDTO = FieldErrorDTO.from(exception.getConstraintViolations());

        return buildResponse(ErrorFieldsDTO
                .from(BAD_REQUEST, ErrorCodes.OS_ERROR_INVALID_ARGUMENTS,"Invalid Arguments", fieldErrorDTO)
                , BAD_REQUEST);
    }

    @ExceptionHandler(HexagonalNotFoundException.class)
    @ResponseStatus(CONFLICT)
    public ResponseEntity<ErrorDTO> handleHexagonalNotFoundException(HexagonalNotFoundException exception) {
        log.error("HexagonalNotFoundException", exception);
        return buildResponse(ErrorDTO
                        .from(CONFLICT, ErrorCodes.OS_ERROR_INVALID_ARGUMENTS, exception.getMessage())
                , CONFLICT);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDTO> handleIllegalArgumentException(IllegalArgumentException exception) {
        log.error("handleIllegalArgumentException", exception);
        return buildResponse(ErrorDTO
                        .from(INTERNAL_SERVER_ERROR, ErrorCodes.OS_ERROR_INVALID_ARGUMENTS, exception.getMessage())
                , INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<ErrorDTO> handleNoSuchElementException(NoSuchElementException exception) {
        log.error("handleNoSuchElementException", exception);
        return buildResponse(ErrorDTO
                        .from(INTERNAL_SERVER_ERROR, ErrorCodes.OS_ERROR_INVALID_ARGUMENTS, exception.getMessage())
                , INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorDTO> handleException(Exception exception) {
        log.error("handleException", exception);
        return buildResponse(ErrorDTO
                .from(INTERNAL_SERVER_ERROR, ErrorCodes.OS_ERROR_INVALID_ARGUMENTS, exception.getMessage())
                , INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorDTO> buildResponse(final ErrorDTO errorDTO, final HttpStatus httpStatus) {
        return new ResponseEntity<>(errorDTO, httpStatus);
    }

    private ResponseEntity<ErrorFieldsDTO> buildResponse(
            final ErrorFieldsDTO errorFieldsDTO, final HttpStatus httpStatus) {
        return new ResponseEntity<>(errorFieldsDTO, httpStatus);
    }
}
