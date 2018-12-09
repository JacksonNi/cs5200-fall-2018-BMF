package edu.northeastern.cs5200.services;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.northeastern.cs5200.models.Faculty;
import edu.northeastern.cs5200.repositories.FacultyRepository;

@RestController
public class FacultyService {
	
	@Autowired
	FacultyRepository facultyRepo;
	
	@PostMapping("/api/register/faculty")
	public Faculty register(@RequestBody Faculty user, HttpSession session) {
		session.setAttribute("currentUser", user);
		return facultyRepo.save(user);
	}

}
