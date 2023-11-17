package com.farizo.superheroe.service.impl;

import com.farizo.superheroe.datasource.repository.SuperheroRepository;
import com.farizo.superheroe.domain.Superhero;
import com.farizo.superheroe.service.CreateRequest;
import com.farizo.superheroe.service.SuperheroService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.farizo.superheroe.domain.Superhero.createSuperhero;
import static java.lang.String.format;

@Service
public class SuperheroServiceImpl implements SuperheroService {

    private final SuperheroRepository superheroRepository;

    public SuperheroServiceImpl(SuperheroRepository superheroRepository) {
        this.superheroRepository = superheroRepository;
    }

    @Override
    public List<Superhero> findAll() {
        return superheroRepository.findAll();
    }

    @Override
    @Cacheable(Superhero.NAME)
    public Superhero findById(Long id) {
        return superheroRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(format("Entity with id %s not found", id)));
    }

    @Override
    public List<Superhero> findByName(String name) {
        List<Superhero> superheros = superheroRepository.findByNameContaining(name);
        return superheros;
    }

    @Override
    @Transactional
    public Superhero create(CreateRequest createRequest) {
        return superheroRepository.save(createSuperhero(createRequest.getName()));
    }

    @Override
    @CacheEvict(Superhero.NAME)
    public void delete(Long id) {
        if (superheroRepository.existsById(id)) {
            superheroRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException(format("Entity with id %s not found", id));
        }
    }

    @Override
    @Transactional
    @CacheEvict(value = Superhero.NAME, key = "id")
    public Superhero update(Long id, CreateRequest updateRequest) {
        Superhero superhero = this.findById(id);
        superhero.setName(updateRequest.getName());
        return superhero;
    }
}
