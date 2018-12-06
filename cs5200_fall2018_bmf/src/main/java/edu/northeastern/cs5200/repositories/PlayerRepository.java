package edu.northeastern.cs5200.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.northeastern.cs5200.models.Player;

public interface PlayerRepository extends CrudRepository<Player, Integer> {
	
	@Query("SELECT player FROM Player player WHERE player.username=:username"
			+ " AND player.password=:password")
	public Player findPlayerByCredentials(
			@Param("username") String username,
			@Param("password") String password);

}
