package edu.northeastern.cs5200.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.northeastern.cs5200.models.Club;
import edu.northeastern.cs5200.models.ClubSeasonData;
import edu.northeastern.cs5200.models.Season;

public interface ClubSeasonDataRepository extends CrudRepository<ClubSeasonData, Integer> {
	
	@Query("SELECT data FROM ClubSeasonData data WHERE data.club=:club AND season=:season")
	public ClubSeasonData findDataByClubAndSeason(@Param("club") Club club, @Param("season") Season season);

}
