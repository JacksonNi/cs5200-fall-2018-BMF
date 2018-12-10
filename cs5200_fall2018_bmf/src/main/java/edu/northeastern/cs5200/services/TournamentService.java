package edu.northeastern.cs5200.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.northeastern.cs5200.models.Tournament;
import edu.northeastern.cs5200.repositories.TournamentRepository;

@Service
public class TournamentService {
	
	@Autowired
	TournamentRepository tourRepo;
	
	public Tournament findById(int id) {
		Optional<Tournament> oTour = tourRepo.findById(id);
		if (oTour.isPresent()) {
			return oTour.get();
		}
		return null;
	}
		

}
