package edu.northeastern.cs5200.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.northeastern.cs5200.models.Admin;
import edu.northeastern.cs5200.models.Coach;
import edu.northeastern.cs5200.repositories.AdminRepository;

@RestController
@CrossOrigin
public class AdminController {
	
	@Autowired
	AdminRepository adminRepo;
	
	@PostMapping("/api/register/admin")
	public Admin register(@RequestBody Admin user, HttpSession session) {
		session.setAttribute("currentUser", user);
		return adminRepo.save(user);
	}
	
	@PostMapping("/api/update/admin")
	public Admin updateAdmin(@RequestBody Admin update) {
		return adminRepo.save(update);
	}

}
