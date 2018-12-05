package edu.northeastern.cs5200.repositories;

import org.springframework.data.repository.CrudRepository;

import edu.northeastern.cs5200.models.Player;

public interface PlayerRepository extends CrudRepository<Player, Integer> {

}
