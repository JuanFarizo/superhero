package com.farizo.superheroe.datasource.repository;

import com.farizo.superheroe.domain.Superhero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuperheroRepository extends JpaRepository<Superhero, Long> {

    List<Superhero> findByNameContaining(String name);

}
