package edu.northeastern.cs5200.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.northeastern.cs5200.models.Season;
import edu.northeastern.cs5200.models.Tournament;
import edu.northeastern.cs5200.repositories.SeasonRepository;
import edu.northeastern.cs5200.services.TournamentService;

@RestController
@CrossOrigin
public class SeasonController {

	@Autowired
	SeasonRepository seasonRepo;
	@Autowired
	TournamentService tourService;

	
	@GetMapping("/api/search/seasons")
	public List<Season> findAllSeasons() {
		return (List<Season>) seasonRepo.findAll();
	}
	
	@GetMapping("/api/search/season")
	public Season findByName(@RequestParam(name="name") String name) {
		return seasonRepo.findBySeasonName(name);
	}
	
	@PostMapping("/api/create/tournament/{id}/season")
	public Season createSeasonForTournament(@PathVariable("id") int tourId, @RequestBody Season season) {
		Tournament tour = tourService.findById(tourId);
		season.setTournament(tour);
		return seasonRepo.save(season);
	}
	

	
	
}
