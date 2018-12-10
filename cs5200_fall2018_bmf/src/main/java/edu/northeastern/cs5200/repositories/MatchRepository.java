package edu.northeastern.cs5200.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.northeastern.cs5200.models.Match;
import edu.northeastern.cs5200.models.Season;

public interface MatchRepository extends CrudRepository<Match, Integer> {
	
	@Query("SELECT match FROM Match match WHERE match.season=:season")
	public List<Match> findMatchesForSeason(@Param("season") Season season);
	

}
