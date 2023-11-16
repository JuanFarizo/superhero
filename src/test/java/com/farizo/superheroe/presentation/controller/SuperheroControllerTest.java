package com.farizo.superheroe.presentation.controller;

import com.farizo.superheroe.service.SuperheroService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;

@WebMvcTest(SuperheroController.class)
class SuperheroControllerTest {
    private static final String CONTEXT_PATH = "/api/superhero";
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SuperheroService superheroService;

    @Test
    void givenIdWhenFindAllTheReturnAllSuperheros() {
        given(superheroService.findAll()).willReturn()
    }

}