package edu.northeastern.cs5200.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.northeastern.cs5200.models.Player;
import edu.northeastern.cs5200.repositories.PlayerRepository;

@RestController
public class PlayerService {
	
	@Autowired
	PlayerRepository pr;
	
	@GetMapping("/api/read/user/players")
	public List<Player> findAllPlayers() {
		return (List<Player>) pr.findAll();
	}
	
	@GetMapping("/api/read/user/player")
	public Player findPlayerByCredentials(
			@RequestParam(name="username") String username, 
			@RequestParam(name="password") String password) {
		
		return pr.findPlayerByCredentials(username, password);
		
	}

}
