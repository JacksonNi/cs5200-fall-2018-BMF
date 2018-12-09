package edu.northeastern.cs5200.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.northeastern.cs5200.models.Season;

public interface SeasonRepository extends CrudRepository<Season, Integer> {
	
	@Query("SELECT season FROM Season season WHERE season.season_id=:id")
	public Season findSeasonBySeasonId(@Param("id") String id);

}
