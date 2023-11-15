package com.farizo.superheroe.presentation.controller;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ResponseExceptionHandler {
    private static final Logger logger = getLogger(ResponseExceptionHandler.class);
    private static final String TITLE_NOT_FOUND = "Not found error";

    @ExceptionHandler(value = {EntityNotFoundException.class})
    protected ResponseEntity<Map<String, Object>> handleNotFound(EntityNotFoundException e) {
        return error(e, NOT_FOUND, TITLE_NOT_FOUND);
    }

    private ResponseEntity<Map<String, Object>> error(Exception exception, HttpStatus status, String title) {
        logger.error(exception.getMessage(), exception);
        Map<String, Object> bodyBuilder = new HashMap<>();
        bodyBuilder.put("title", title);
        bodyBuilder.put("status", status.value());
        bodyBuilder.put("exception", exception.getMessage());
        return ResponseEntity.status(status.value()).body(bodyBuilder);
    }
}
