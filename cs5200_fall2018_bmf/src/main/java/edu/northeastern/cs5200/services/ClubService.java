package edu.northeastern.cs5200.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.northeastern.cs5200.models.Club;
import edu.northeastern.cs5200.repositories.ClubRepository;

@Service
public class ClubService {
	
	@Autowired
	ClubRepository clubRepo;
	
	
	public Club findById(int id) {
		Optional<Club> oclub = clubRepo.findById(id);
		if (oclub.isPresent()) {
			return oclub.get();
		}
		return null;
	}

}
