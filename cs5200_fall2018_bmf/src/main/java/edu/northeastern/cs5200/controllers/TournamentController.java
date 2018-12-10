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

import edu.northeastern.cs5200.models.Faculty;
import edu.northeastern.cs5200.models.Tournament;
import edu.northeastern.cs5200.repositories.TournamentRepository;
import edu.northeastern.cs5200.services.FacultyService;

@RestController
@CrossOrigin
public class TournamentController {

	@Autowired
	TournamentRepository tourRepo;
	@Autowired
	FacultyService facultyService;
	
	@GetMapping("/api/search/tournaments")
	public List<Tournament> findAllTournaments() {
		return (List<Tournament>) tourRepo.findAll();
	}
	
	@GetMapping("/api/search/tournament")
	public Tournament findByName(@RequestParam(name="name") String name) {
		return tourRepo.findByTournamentName(name);
	}
	
	@PostMapping("/api/create/faculty/{id}/tournament")
	public Tournament createTournamentForFaculty(@PathVariable("id") int fid, @RequestBody Tournament tour) {
		Faculty faculty = facultyService.findById(fid);
		tour.setFaculty(faculty);
		return tourRepo.save(tour);
	}
	

	

}
