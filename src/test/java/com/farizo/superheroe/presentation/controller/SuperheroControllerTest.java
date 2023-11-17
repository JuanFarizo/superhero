package com.farizo.superheroe.presentation.controller;

import com.farizo.superheroe.domain.Superhero;
import com.farizo.superheroe.service.SuperheroService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SuperheroController.class)
class SuperheroControllerTest {
    private static final String CONTEXT_PATH = "/api/superhero/";
    @Autowired
    private MockMvc mvc;
    @MockBean
    private SuperheroService superheroService;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void givenIdWhenFindAllTheReturnAllSuperheros() throws Exception {
        List<Superhero> superheros = List.of(Superhero.createSuperhero("MockedName"));
        given(superheroService.findAll()).willReturn(superheros);
        mvc.perform(get(CONTEXT_PATH)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(superheros)))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void givenExceptionWhenFindByIdThenReturn4xx() throws Exception {
        Long superheroId = 1L;
        given(superheroService.findById(superheroId)).willThrow(EntityNotFoundException.class);
        mvc.perform(get(CONTEXT_PATH + superheroId)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

}