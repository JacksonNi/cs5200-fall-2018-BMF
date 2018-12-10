package edu.northeastern.cs5200.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.northeastern.cs5200.models.Season;
import edu.northeastern.cs5200.repositories.SeasonRepository;

@Service
public class SeasonService {
	
	@Autowired
	SeasonRepository seasonRepo;
	
	public Season findById(int id) {
		Optional<Season> oseason = seasonRepo.findById(id);
		if (oseason.isPresent()) {
			Season season = oseason.get();
			return season;
		}
		return null;
	}

}
