package com.lz.hexagonal.arch.infra.rest;

import com.lz.hexagonal.arch.infra.rest.dto.ErrorDTO;
import com.lz.hexagonal.arch.infra.rest.dto.ErrorFieldsDTO;
import com.lz.hexagonal.arch.infra.rest.dto.FieldErrorDTO;
import com.lz.hexagonal.arch.infra.rest.dto.OSErrorCodes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;

@Slf4j
@RestControllerAdvice
public class RestErrorHandle {

    /**
     * Handle HttpMessageNotReadableException. Happens when request JSON is malformed.
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<ErrorDTO> handleHttpMessageNotReadable(
            HttpMessageNotReadableException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.trace("Returning HTTP 409 Conflict");

        return buildResponse(
                ErrorDTO.from(BAD_REQUEST, OSErrorCodes.OS_ERROR_INVALID_ARGUMENTS, exception.getMessage()));
    }

    /**
     * Handle MethodArgumentNotValidException. Errors from Java Bean Validation.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorFieldsDTO handleMethodArgumentNotValidException(final MethodArgumentNotValidException exception) {
        log.trace("MethodArgumentNotValidException");

        List<FieldErrorDTO> fieldErrorDTO =FieldErrorDTO.from(exception);

//        List<FieldErrorDTO> fieldErrorDTO = exception
//                .getBindingResult()
//                .getFieldErrors()
//                .stream()
//                .map(FieldErrorDTO::from)
//                .collect(Collectors.toList());

        return ErrorFieldsDTO
                .from(BAD_REQUEST, OSErrorCodes.OS_ERROR_INVALID_ARGUMENTS,"Invalid Arguments", fieldErrorDTO);
    }

    /**
     * Handle javax.validation.ConstraintViolationException. Thrown when @Validated fails.
     */
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<ErrorFieldsDTO> handleConstraintViolation(ConstraintViolationException exception) {
        log.trace("ConstraintViolationException");
        List<FieldErrorDTO> fieldErrorDTO = FieldErrorDTO.from(exception.getConstraintViolations());

        return new ResponseEntity<ErrorFieldsDTO>(ErrorFieldsDTO
                .from(BAD_REQUEST, OSErrorCodes.OS_ERROR_INVALID_ARGUMENTS,"Invalid Arguments", fieldErrorDTO)
                , CONFLICT);
    }

    /**
     * Handle java.lang.IllegalArgumentException.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorDTO handleIllegalArgumentException(IllegalArgumentException e) {
        log.info("IllegalArgumentException");
        return ErrorDTO
                .from(CONFLICT, OSErrorCodes.OS_ERROR_INVALID_ARGUMENTS,"Invalid Arguments");
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorDTO handleNoSuchElementException(NoSuchElementException e) {
        log.trace("IllegalArgumentException");
        return ErrorDTO
                .from(BAD_REQUEST, OSErrorCodes.OS_ERROR_INVALID_ARGUMENTS,"Invalid Arguments");
    }

    private ResponseEntity<ErrorDTO> buildResponse(ErrorDTO errorDTO) {
        return new ResponseEntity<ErrorDTO>(errorDTO, HttpStatus.ACCEPTED);
    }

     /*
     * Handle Java assert when falls.
     */
//    @ExceptionHandler(AssertionError.class)
//    public ResponseEntity<ErrorDTO> assertionError(AssertionError error) {
//        log.trace("AssertionError");
//        return new ResponseEntity<ErrorDTO>(ErrorDTO
//                .from(BAD_REQUEST, OSErrorCodes.OS_ERROR_INVALID_ARGUMENTS, error.getMessage()), CONFLICT);
//    }
}
