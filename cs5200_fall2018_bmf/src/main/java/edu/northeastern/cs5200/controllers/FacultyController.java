package edu.northeastern.cs5200.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.northeastern.cs5200.models.Faculty;
import edu.northeastern.cs5200.repositories.FacultyRepository;

@RestController
public class FacultyController {
	
	@Autowired
	FacultyRepository facultyRepo;
	
	@PostMapping("/api/register/faculty")
	public Faculty register(@RequestBody Faculty user, HttpSession session) {
		session.setAttribute("currentUser", user);
		return facultyRepo.save(user);
	}
	
	@GetMapping("/api/search/faculties")
	public List<Faculty> finfAllFaculty() {
		return (List<Faculty>) facultyRepo.findAll();
	}
	
}
