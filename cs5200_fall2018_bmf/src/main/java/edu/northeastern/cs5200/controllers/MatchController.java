package edu.northeastern.cs5200.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.northeastern.cs5200.models.Match;
import edu.northeastern.cs5200.models.Season;
import edu.northeastern.cs5200.repositories.MatchRepository;
import edu.northeastern.cs5200.repositories.SeasonRepository;
import edu.northeastern.cs5200.services.SeasonService;

@RestController
@CrossOrigin
public class MatchController {

	@Autowired
	MatchRepository matchRepo;
	@Autowired
	SeasonRepository seasonRepo;
	@Autowired
	SeasonService seasonService;
	
	@GetMapping("/api/search/season/{id}/matches")
	public List<Match> findAllMatches(@PathVariable("id") int seasonId) {
		Season season = seasonService.findById(seasonId);
		return season.getMatches();
	}
	
	
	@PostMapping("/api/create/season/{id}/match")
	public Match createMatchForSeason(@PathVariable("id") int seasonId, @RequestBody Match match) {
		Season season = seasonService.findById(seasonId);
		match.setSeason(season);
		return matchRepo.save(match);
	}
	
	
}
