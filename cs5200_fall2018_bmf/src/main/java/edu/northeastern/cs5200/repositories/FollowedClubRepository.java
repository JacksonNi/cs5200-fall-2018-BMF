package edu.northeastern.cs5200.repositories;

import org.springframework.data.repository.CrudRepository;

import edu.northeastern.cs5200.models.FollowedClub;

public interface FollowedClubRepository extends CrudRepository<FollowedClub, Integer> {

}
