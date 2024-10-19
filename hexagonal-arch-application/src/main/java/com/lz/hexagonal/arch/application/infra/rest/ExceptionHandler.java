package com.lz.hexagonal.arch.application.infra.rest;

import com.lz.hexagonal.arch.application.infra.rest.dto.ErrorDTO;
import com.lz.hexagonal.arch.application.infra.rest.dto.ErrorFieldsDTO;
import com.lz.hexagonal.arch.application.infra.rest.dto.FieldErrorDTO;
import com.lz.hexagonal.arch.domain.infra.ErrorCodes;
import com.lz.hexagonal.arch.domain.infra.exceptions.HexagonalInternalException;
import com.lz.hexagonal.arch.domain.infra.exceptions.HexagonalNotFoundException;
import com.lz.hexagonal.arch.domain.infra.exceptions.HexagonalResourceConflictException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class ExceptionHandler {

    static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    /**
     * Handle HexagonalResourceConflictException. Happens when a resource already exists.
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(HexagonalResourceConflictException.class)
    @ResponseStatus(CONFLICT)
    public ResponseEntity<ErrorDTO> handleHexagonalResourceAlreadyExistsException(
            HexagonalResourceConflictException exception, HttpServletRequest request) {
        logger.error("HexagonalResourceAlreadyExistsException", exception);
        return buildConflictResponse(exception.getMessage(), request.getRequestURI());
    }

    /**
     * Handle HexagonalNotFoundException. Happens when a resource is not found.
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(HexagonalNotFoundException.class)
    @ResponseStatus(CONFLICT)
    public ResponseEntity<ErrorDTO> handleHexagonalNotFoundException(
            HexagonalNotFoundException exception, HttpServletRequest request) {
        logger.error("HexagonalNotFoundException", exception);
        return buildConflictResponse(exception.getMessage(), request.getRequestURI());
    }

    /**
     * Handle HexagonalInternalException. Happens when an internal error occurs.
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(HexagonalInternalException.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorDTO> handleHexagonalInternalException(
            HexagonalInternalException exception, HttpServletRequest request) {
        logger.error(exception.getMessage(), exception.getThrowable());

        return buildResponse(ErrorDTO
                        .from(INTERNAL_SERVER_ERROR, ErrorCodes.OS_ERROR_INVALID_ARGUMENTS,
                                "An unexpected error occurred while "+exception.getMessage(),
                                request.getRequestURI())
                , INTERNAL_SERVER_ERROR);
    }

    /**
     * Handle HttpMessageNotReadableException. Happens when request JSON is malformed.
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<ErrorDTO> handleHttpMessageNotReadable(
            HttpMessageNotReadableException exception, HttpServletRequest request) {
        logger.error("handleHttpMessageNotReadable", exception);

        var message = exception.getMessage();

        if (exception.getCause() != null
                && exception.getCause().getCause() != null
                && exception.getCause().getCause().getLocalizedMessage() != null)
            message = exception.getCause().getCause().getLocalizedMessage();

        return buildResponse(ErrorDTO
                        .from(BAD_REQUEST, ErrorCodes.OS_ERROR_INVALID_ARGUMENTS, message, request.getRequestURI())
                , BAD_REQUEST);
    }

    /**
     * Handle MethodArgumentNotValidException. Triggered when an object fails @Valid validation.
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<ErrorFieldsDTO> handleMethodArgumentNotValidException(
            final MethodArgumentNotValidException exception) {
        logger.error("handleMethodArgumentNotValidException", exception);
        List<FieldErrorDTO> fieldErrorDTO = FieldErrorDTO.from(exception.getBindingResult().getFieldErrors());

        return buildResponse(ErrorFieldsDTO.from(
                                BAD_REQUEST,
                                ErrorCodes.OS_ERROR_INVALID_ARGUMENTS,
                                "Invalid Arguments",
                                fieldErrorDTO)
                , BAD_REQUEST);
    }

    /**
     * Handle ConstraintViolationException. Thrown when @Validated fails.
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<ErrorFieldsDTO> handleConstraintViolation(ConstraintViolationException exception) {
        logger.error("handleConstraintViolation", exception);
        List<FieldErrorDTO> fieldErrorDTO = FieldErrorDTO.from(exception.getConstraintViolations());

        return buildResponse(ErrorFieldsDTO
                .from(BAD_REQUEST, ErrorCodes.OS_ERROR_INVALID_ARGUMENTS,"Invalid Arguments", fieldErrorDTO)
                , BAD_REQUEST);
    }

    /**
     * Handle IllegalArgumentException. Thrown when an argument is illegal.
     * @param exception
     * @return
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDTO> handleIllegalArgumentException(
            IllegalArgumentException exception, HttpServletRequest request) {
        logger.error("handleIllegalArgumentException", exception);
        return buildResponse(ErrorDTO
                        .from(INTERNAL_SERVER_ERROR, ErrorCodes.OS_ERROR_INVALID_ARGUMENTS, exception.getMessage(), request.getRequestURI())
                , INTERNAL_SERVER_ERROR);
    }

    /**
     * Handle NoSuchElementException. Thrown when an element is not found.
     * @param exception
     * @return
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<ErrorDTO> handleNoSuchElementException(
            NoSuchElementException exception, HttpServletRequest request) {
        logger.error("handleNoSuchElementException", exception);
        return buildResponse(ErrorDTO
                        .from(INTERNAL_SERVER_ERROR, ErrorCodes.OS_ERROR_INVALID_ARGUMENTS, exception.getMessage(), request.getRequestURI())
                , INTERNAL_SERVER_ERROR);
    }

    /**
     * Handle Exception. Catch all exceptions.
     * @param exception
     * @return
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorDTO> handleException(Exception exception, HttpServletRequest request) {
        logger.error("handleException", exception);
        return buildResponse(ErrorDTO
                .from(INTERNAL_SERVER_ERROR, ErrorCodes.OS_ERROR_INVALID_ARGUMENTS, exception.getMessage(), request.getRequestURI())
                , INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorDTO> buildResponse(final ErrorDTO errorDTO, final HttpStatus httpStatus) {
        return new ResponseEntity<>(errorDTO, httpStatus);
    }

    private ResponseEntity<ErrorFieldsDTO> buildResponse(
            final ErrorFieldsDTO errorFieldsDTO, final HttpStatus httpStatus) {
        return new ResponseEntity<>(errorFieldsDTO, httpStatus);
    }

    public ResponseEntity<ErrorDTO> buildConflictResponse(String message, String request) {
        return buildResponse(ErrorDTO
                .from(CONFLICT, ErrorCodes.OS_ERROR_INVALID_ARGUMENTS, message, request), CONFLICT);
    }
}
