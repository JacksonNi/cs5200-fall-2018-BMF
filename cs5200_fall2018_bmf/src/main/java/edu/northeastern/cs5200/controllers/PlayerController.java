package edu.northeastern.cs5200.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.northeastern.cs5200.models.Player;
import edu.northeastern.cs5200.repositories.PlayerRepository;

@RestController
public class PlayerController {
	
	@Autowired
	PlayerRepository pr;
	
	@PostMapping("/api/register/player")
	public Player register(@RequestBody Player user, HttpSession session) {
		session.setAttribute("currentUser", user);
		return pr.save(user);
	}
	
	
	@GetMapping("/api/search/players")
	public List<Player> findAllPlayers() {
		return (List<Player>) pr.findAll();
	}
	
	
	

}
