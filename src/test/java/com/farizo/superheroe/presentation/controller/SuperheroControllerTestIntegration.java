package com.farizo.superheroe.presentation.controller;

import com.farizo.superheroe.Application;
import com.farizo.superheroe.domain.Superhero;
import com.farizo.superheroe.service.CreateRequest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest(classes = Application.class, webEnvironment = RANDOM_PORT)
class SuperheroControllerTestIntegration {

    private static final String CONTEXT_PATH = "/api/superhero";
    @Autowired
    protected TestRestTemplate template;

    @Test
    void givenNonPathVariableWhenFindAllReturnOkAndSuperheros() {
        ResponseEntity<List<Superhero>> response = template.exchange(
                CONTEXT_PATH + "/",
                GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );
        assertThat(response.getStatusCode(), is(OK));
        assertNotNull(response.getBody());
    }

    @Test
    void givenIdWhenFindByIdTheReturnSuperhero() {
        String superheroId = "1";
        ResponseEntity<Superhero> response = template.exchange(
                CONTEXT_PATH + "/" + superheroId,
                GET,
                null,
                Superhero.class
        );
        assertThat(response.getStatusCode(), is(OK));
        assertNotNull(response.getBody());
    }

    @Test
    void givenPartialNameWhenFindByNameThenReturnOkAndSuperheros() {
        String superheroName = "man";
        ResponseEntity<List<Superhero>> response = template.exchange(
                CONTEXT_PATH + "/name/" + superheroName,
                GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );
        assertThat(response.getStatusCode(), is(OK));
        assertNotNull(response.getBody());
    }

    @Test
    void givenRequestCreationWhenCreateThenReturnSuperhero() {
        String superheroName = "mockedName";
        ResponseEntity<Superhero> response = template.exchange(
                CONTEXT_PATH + "/",
                POST,
                new ResponseEntity<>(new CreateRequest(superheroName), HttpStatusCode.valueOf(200)),
                Superhero.class
        );
        assertThat(response.getStatusCode(), is(OK));
        assertNotNull(response.getBody());
        assertEquals(superheroName, response.getBody().getName());
    }

    @Test
    @Disabled
    void givenRequestCreationWhenUpdateThenReturnSuperheroUpdated() {
        String superheroName = "newMockedName";
        Long superheroId = 1L;
        ResponseEntity<Superhero> exchange = template.exchange(
                CONTEXT_PATH + "/" + superheroId,
                PATCH,
                new ResponseEntity<>(new CreateRequest(superheroName), HttpStatusCode.valueOf(200)),
                Superhero.class
        );
        assertThat(exchange.getStatusCode(), is(OK));
        assertNotNull(exchange.getBody());
        assertEquals(superheroId, exchange.getBody().getId());
        assertEquals(superheroName, exchange.getBody().getName());
    }

    @Test
    void givenIdWhenDeleteThenReturnOk() {
        Long superheroId = 1L;
        ResponseEntity<Superhero> response = template.exchange(
                CONTEXT_PATH + "/" + superheroId,
                DELETE,
                null,
                Superhero.class
        );
        assertThat(response.getStatusCode(), is(OK));
        assertNull(response.getBody());
    }

}