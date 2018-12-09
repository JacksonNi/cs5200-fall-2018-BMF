package edu.northeastern.cs5200.repositories;

import org.springframework.data.repository.CrudRepository;

import edu.northeastern.cs5200.models.Jersey;

public interface JerseyRepository extends CrudRepository<Jersey, Integer> {

}
