package edu.northeastern.cs5200.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.northeastern.cs5200.models.Away;
import edu.northeastern.cs5200.models.Home;
import edu.northeastern.cs5200.models.Match;
import edu.northeastern.cs5200.repositories.AwayRepository;
import edu.northeastern.cs5200.services.MatchService;

@RestController
@CrossOrigin
public class AwayController {
	
	@Autowired
	MatchService matchService;
	@Autowired
	AwayRepository awayRepo;
	
	@PostMapping("/api/create/match/{id}/away")
	public Away createAway(@PathVariable("id") int matchId, @RequestBody Away away) {
		Match match = matchService.findById(matchId);
		away.setMatch(match);
		return awayRepo.save(away);
	}
	
	@GetMapping("/api/search/match/{id}/away")
	public Away getAwayForMatch(@PathVariable("id") int matchId) {
		Match match = matchService.findById(matchId);
		return match.getAway();
	}

}
