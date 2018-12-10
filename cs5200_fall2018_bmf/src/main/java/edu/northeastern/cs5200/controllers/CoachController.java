package edu.northeastern.cs5200.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.northeastern.cs5200.models.Coach;
import edu.northeastern.cs5200.repositories.CoachRepository;

@RestController
@CrossOrigin
public class CoachController {
	
	@Autowired
	CoachRepository coachRepo;
	
	@PostMapping("/api/register/coach")
	public Coach register(@RequestBody Coach user, HttpSession session) {
		session.setAttribute("currentUser", user);
		return coachRepo.save(user);
	}
	
	@PostMapping("/api/update/coach")
	public Coach updateCoach(@RequestBody Coach update) {
		return coachRepo.save(update);
	}

}
