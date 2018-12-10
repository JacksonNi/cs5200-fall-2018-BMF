package edu.northeastern.cs5200.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.northeastern.cs5200.models.Faculty;
import edu.northeastern.cs5200.repositories.FacultyRepository;

@Service
public class FacultyService {

	@Autowired
	FacultyRepository facultyRepo;
	
	public Faculty findById(int id) {
		Optional<Faculty> oFaculty = facultyRepo.findById(id);
		if (oFaculty.isPresent()) {
			return oFaculty.get();
		}
		return null;
	}
	
}
