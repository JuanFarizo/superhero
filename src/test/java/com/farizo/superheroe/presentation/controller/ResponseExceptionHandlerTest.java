package com.farizo.superheroe.presentation.controller;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.http.HttpStatus.NOT_FOUND;

class ResponseExceptionHandlerTest {
    private static final ResponseExceptionHandler handler = new ResponseExceptionHandler();

    @Test
    void givenEntityNotFoundExceptionWhenHandleNotFoundThenReturnNotFound() {
        ResponseEntity<Map<String, Object>> response = handler.handleNotFound(
                new EntityNotFoundException("I am an entityNotFoundException")
        );
        assertThat(response.getStatusCode(), is(NOT_FOUND));
    }
}