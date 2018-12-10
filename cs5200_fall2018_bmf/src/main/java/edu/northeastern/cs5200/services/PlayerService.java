package edu.northeastern.cs5200.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.northeastern.cs5200.models.Player;
import edu.northeastern.cs5200.repositories.PlayerRepository;

@Service
public class PlayerService {
	
	@Autowired
	PlayerRepository playerRepo;
	
	
	public Player findById(int id) {
		Optional<Player> oPlayer = playerRepo.findById(id);
		if (oPlayer.isPresent()) {
			return oPlayer.get();
		} 
		return null;
	}

}
