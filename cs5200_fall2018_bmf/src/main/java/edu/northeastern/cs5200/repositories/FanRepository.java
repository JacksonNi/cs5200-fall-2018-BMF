package edu.northeastern.cs5200.repositories;

import org.springframework.data.repository.CrudRepository;

import edu.northeastern.cs5200.models.Fan;

public interface FanRepository extends CrudRepository<Fan, Integer> {

}
