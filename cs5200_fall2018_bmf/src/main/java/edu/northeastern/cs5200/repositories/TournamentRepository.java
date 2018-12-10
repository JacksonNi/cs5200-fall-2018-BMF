package edu.northeastern.cs5200.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.northeastern.cs5200.models.Tournament;

public interface TournamentRepository extends CrudRepository<Tournament, Integer> {
	
	@Query("SELECT tour FROM Tournament tour WHERE tour.name=:name")
	public Tournament findByTournamentName(@Param("name") String name);

}
