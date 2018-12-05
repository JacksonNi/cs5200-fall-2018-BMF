package edu.northeastern.cs5200.repositories;

import org.springframework.data.repository.CrudRepository;

import edu.northeastern.cs5200.models.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {

}
