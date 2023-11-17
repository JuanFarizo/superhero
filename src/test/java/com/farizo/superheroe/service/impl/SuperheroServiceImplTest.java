package com.farizo.superheroe.service.impl;

import com.farizo.superheroe.datasource.repository.SuperheroRepository;
import com.farizo.superheroe.domain.Superhero;
import com.farizo.superheroe.service.CreateRequest;
import com.farizo.superheroe.service.SuperheroService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SuperheroServiceImpl.class})
class SuperheroServiceImplTest {

    @Autowired
    private SuperheroService superheroService;

    @MockBean
    private SuperheroRepository repository;

    @Test
    void givenEmptyListWhenFindAllThenReturnEmptyList() {
        given(repository.findAll()).willReturn(emptyList());
        List<Superhero> superheros = superheroService.findAll();
        assertTrue(superheros.isEmpty());
    }

    @Test
    void givenEmptyOptionalWhenFindByIdThenThrowException() {
        given(repository.findById(anyLong())).willReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> superheroService.findById(1L));
    }

    @Test
    void givenSuperheroWhenCreateThenReturnSuperhero() {
        Superhero mockedSuperhero = Superhero.createSuperhero("MockedSuperhero");
        mockedSuperhero.setId(1L);
        given(repository.save(any())).willReturn(mockedSuperhero);
        Superhero superhero = superheroService.create(new CreateRequest("MockedSuperhero"));
        assertEquals(mockedSuperhero.getId(), superhero.getId());
    }
}