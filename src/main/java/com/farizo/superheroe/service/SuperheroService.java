package com.farizo.superheroe.service;

import com.farizo.superheroe.domain.Superhero;

import java.util.List;

public interface SuperheroService {
    List<Superhero> findAll();

    Superhero findById(Long id);

    List<Superhero> findByName(String name);

    Superhero create(CreateRequest createRequest);

    void delete(Long id);

    Superhero update(Long id, CreateRequest updateRequest);

}
