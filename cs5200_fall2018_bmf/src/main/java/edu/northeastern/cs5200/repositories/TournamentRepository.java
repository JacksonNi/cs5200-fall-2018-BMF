package edu.northeastern.cs5200.repositories;

import org.springframework.data.repository.CrudRepository;

import edu.northeastern.cs5200.models.Tournament;

public interface TournamentRepository extends CrudRepository<Tournament, Integer> {

}
