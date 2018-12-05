package edu.northeastern.cs5200.repositories;

import org.springframework.data.repository.CrudRepository;

import edu.northeastern.cs5200.models.Season;

public interface SeasonRepository extends CrudRepository<Season, Integer> {

}
