package com.mastery.java.task.rest.exhandling;

import com.mastery.java.task.dto.exception.ErrorMessage;
import com.mastery.java.task.dto.exception.ResourceNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.lang.invoke.MethodHandles;
import java.time.LocalDate;

@RestControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage resourceNotFoundException(ResourceNotFoundException ex, WebRequest request)
    {
        log.error("Exception: {}. Description: {}",ex.getMessage(), request.getDescription(false));
        return new ErrorMessage(HttpStatus.NOT_FOUND.value(),
                LocalDate.now(), ex.getMessage(), request.getDescription(false));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage globalException(Exception ex, WebRequest request) {
        log.error("Exception: {}. Description: {}",ex.getMessage(), request.getDescription(false));
        return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDate.now(), ex.getMessage(), request.getDescription(false));
    }
}
