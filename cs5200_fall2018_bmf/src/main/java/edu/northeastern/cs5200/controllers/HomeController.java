package edu.northeastern.cs5200.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.northeastern.cs5200.models.Home;
import edu.northeastern.cs5200.models.Match;
import edu.northeastern.cs5200.repositories.HomeRepository;
import edu.northeastern.cs5200.repositories.MatchRepository;
import edu.northeastern.cs5200.services.MatchService;

@RestController
@CrossOrigin
public class HomeController {
	
	@Autowired
	MatchService matchService;
	@Autowired
	HomeRepository homeRepo;
	@Autowired
	MatchRepository matchRepo;
	
	@PostMapping("/api/create/match/{id}/home")
	public Home createHome(@PathVariable("id") int matchId, @RequestBody Home home) {
		Match match = matchService.findById(matchId);
		home.setMatch(match);
		return homeRepo.save(home);
	}
	
	@GetMapping("/api/search/match/{id}/home")
	public Home getHomeForMatch(@PathVariable("id") int matchId) {
		Match match = matchService.findById(matchId);
		return match.getHome();
	}

}
