package edu.northeastern.cs5200.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.northeastern.cs5200.models.Coach;
import edu.northeastern.cs5200.repositories.CoachRepository;

@Service
public class CoachService {
	
	@Autowired
	CoachRepository coachRepo;
	
	public Coach findById(int id) {
		Optional<Coach> oCoach = coachRepo.findById(id);
		if (oCoach.isPresent()) {
			return oCoach.get();
		}
		return null;
	}

}
