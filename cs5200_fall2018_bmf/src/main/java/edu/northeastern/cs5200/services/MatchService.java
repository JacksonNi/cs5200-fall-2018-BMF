package edu.northeastern.cs5200.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.northeastern.cs5200.models.Match;
import edu.northeastern.cs5200.repositories.MatchRepository;

@Service
public class MatchService {
	
	@Autowired
	MatchRepository matchRepo;
	
	public Match findById(int id) {
		Optional<Match> oMatch = matchRepo.findById(id);
		if (oMatch.isPresent()) {
			return oMatch.get();
		}
		return null;
	}

}
