package edu.northeastern.cs5200.repositories;

import org.springframework.data.repository.CrudRepository;

import edu.northeastern.cs5200.models.FollowedPlayer;

public interface FollowedPlayerRepository extends CrudRepository<FollowedPlayer, Integer> {

}
