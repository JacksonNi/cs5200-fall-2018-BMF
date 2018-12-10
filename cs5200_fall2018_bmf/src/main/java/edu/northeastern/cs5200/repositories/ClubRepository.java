package edu.northeastern.cs5200.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.northeastern.cs5200.models.Club;

public interface ClubRepository extends CrudRepository<Club, Integer> {
	
	@Query("SELECT club FROM Club club WHERE club.clubId=:id")
	public Club findClubByClubId(@Param("id") String id);
	
	@Query("SELECT club FROM Club club WHERE club.name=:name")
	public Club findByClubName(@Param("name") String name);

}
