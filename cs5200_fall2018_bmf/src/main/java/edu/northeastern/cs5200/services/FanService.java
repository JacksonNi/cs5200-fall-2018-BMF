package edu.northeastern.cs5200.services;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.northeastern.cs5200.models.Fan;
import edu.northeastern.cs5200.repositories.FanRepository;

@RestController
public class FanService {

	
	@Autowired
	FanRepository fanRepo;
	
	@PostMapping("/api/register/fan")
	public Fan register(@RequestBody Fan user, HttpSession session) {
		session.setAttribute("currentUser", user);
		return fanRepo.save(user);
	}
	
}
