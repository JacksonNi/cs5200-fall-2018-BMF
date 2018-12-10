package edu.northeastern.cs5200.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.northeastern.cs5200.models.Away;
import edu.northeastern.cs5200.repositories.AwayRepository;

@Service
public class AwayService {
	
	@Autowired
	AwayRepository awayRepo;
	
	public Away findById(int id) {
		Optional<Away> oAway = awayRepo.findById(id);
		if (oAway.isPresent()) {
			return oAway.get();
		}
		return null;
	}

}
