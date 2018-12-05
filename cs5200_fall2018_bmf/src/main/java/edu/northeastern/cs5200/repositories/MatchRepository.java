package edu.northeastern.cs5200.repositories;

import org.springframework.data.repository.CrudRepository;

import edu.northeastern.cs5200.models.Match;

public interface MatchRepository extends CrudRepository<Match, Integer> {

}
