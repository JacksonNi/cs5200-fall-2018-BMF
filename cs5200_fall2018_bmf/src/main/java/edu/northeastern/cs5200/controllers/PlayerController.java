package edu.northeastern.cs5200.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.northeastern.cs5200.models.Player;
import edu.northeastern.cs5200.repositories.PlayerRepository;
import edu.northeastern.cs5200.services.PlayerService;

@RestController
@CrossOrigin
public class PlayerController {
	
	@Autowired
	PlayerRepository pr;
	@Autowired
	PlayerService playerService;
	
	@PostMapping("/api/register/player")
	public Player register(@RequestBody Player user, HttpSession session) {
		session.setAttribute("currentUser", user);
		return pr.save(user);
	}
	
	
	@GetMapping("/api/search/players")
	public List<Player> findAllPlayers() {
		return (List<Player>) pr.findAll();
	}
	
	@GetMapping("/api/search/player/{id}")
	public Player findByPlayerId(@PathVariable("id") String id) {
		return pr.findPlayerByPlayerId(id);
	}
	
	
	@PostMapping("/api/update/player")
	public Player updatePlayer(@RequestBody Player update) {
		return pr.save(update);
//		Player player = playerService.findById(update.getId());
//		if (player != null) {
//			return pr.save(player);			
//		}
//		return null;
	}
	
	
	

}
