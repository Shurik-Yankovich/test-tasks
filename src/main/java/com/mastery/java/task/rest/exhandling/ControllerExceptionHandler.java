package com.mastery.java.task.rest.exhandling;

import com.mastery.java.task.dto.exception.ErrorMessage;
import com.mastery.java.task.dto.exception.ResourceNotFoundException;

import com.mastery.java.task.dto.exception.ValidationErrorMessage;
import com.mastery.java.task.dto.exception.Violation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolationException;
import java.lang.invoke.MethodHandles;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final String VALIDATION_FAILED_MESSAGE = "Validation failed";

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        log.error("Exception: {}.\nDescription: {}", ex.getMessage(), request.getDescription(false));

        return new ErrorMessage(HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ValidationErrorMessage constraintValidationException(ConstraintViolationException ex, WebRequest request) {
        final List<Violation> violations = ex.getConstraintViolations().stream()
                .map(violation -> new Violation(violation.getPropertyPath().toString(), violation.getMessage()))
                .collect(Collectors.toList());

        log.error("Exception: {}.\nViolation: {}\nDescription: {}",
                VALIDATION_FAILED_MESSAGE, violations, request.getDescription(false));

        return new ValidationErrorMessage(VALIDATION_FAILED_MESSAGE,
                violations, request.getDescription(false));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ValidationErrorMessage methodArgumentNotValidException (MethodArgumentNotValidException ex, WebRequest request) {
        final List<Violation> violations = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new Violation(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());

        log.error("Exception: {}.\nViolation: {}\nDescription: {}",
                VALIDATION_FAILED_MESSAGE, violations, request.getDescription(false));

        return new ValidationErrorMessage(VALIDATION_FAILED_MESSAGE,
                violations, request.getDescription(false));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage globalException(Exception ex, WebRequest request) {
        log.error("Exception: {}.\nDescription: {}", ex.getMessage(), request.getDescription(false));
        
        return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
    }
}
