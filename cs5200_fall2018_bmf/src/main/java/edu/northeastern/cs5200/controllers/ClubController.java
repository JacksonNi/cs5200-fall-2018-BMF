package edu.northeastern.cs5200.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.northeastern.cs5200.models.Club;
import edu.northeastern.cs5200.repositories.ClubRepository;

@RestController
@CrossOrigin
public class ClubController {
	
	@Autowired
	ClubRepository clubRepo;

	@PostMapping("/api/create/club")
	public Club createNewClub(@RequestBody Club club) {
		return clubRepo.save(club);
	}
	
	@GetMapping("/api/search/club")
	public Club findByName(@RequestParam(name="name") String name) {
		return clubRepo.findByClubName(name);
	}
	
	@GetMapping("/api/search/club/{id}")
	public Club findById(@PathVariable("id") String club_id) {
		return clubRepo.findClubByClubId(club_id);
	}
}
