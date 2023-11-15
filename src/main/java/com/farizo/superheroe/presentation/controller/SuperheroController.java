package com.farizo.superheroe.presentation.controller;

import com.farizo.superheroe.domain.Superhero;
import com.farizo.superheroe.infrastructure.CallMetricCollectorLog;
import com.farizo.superheroe.service.CreateRequest;
import com.farizo.superheroe.service.SuperheroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/superhero")
public class SuperheroController {

    private final SuperheroService superheroService;

    public SuperheroController(SuperheroService superheroService) {
        this.superheroService = superheroService;
    }

    @CallMetricCollectorLog
    @GetMapping(value = "/", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Superhero>> findAll() {
        return ok(superheroService.findAll());
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Superhero> findById(@PathVariable("id") Long id) {
        return ok(superheroService.findById(id));
    }

    @GetMapping(value = "/name/{name}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Superhero>> findByName(@PathVariable("name") String name) {
        return ok(superheroService.findByName(name));
    }

    @CallMetricCollectorLog
    @PostMapping(value = "/", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Superhero> create(
            @RequestBody final CreateRequest request
    ) {
        return ok(superheroService.create(request));
    }

    @DeleteMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity delete(
            @PathVariable("id") Long id
    ) {
        superheroService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Superhero> update(
            @PathVariable("id") Long id,
            @RequestBody final CreateRequest updateRequest
    ) {
        return ok(superheroService.update(id, updateRequest));
    }
}
