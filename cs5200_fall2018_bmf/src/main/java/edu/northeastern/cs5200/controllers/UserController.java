package edu.northeastern.cs5200.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.northeastern.cs5200.models.Admin;
import edu.northeastern.cs5200.models.Coach;
import edu.northeastern.cs5200.models.Faculty;
import edu.northeastern.cs5200.models.Fan;
import edu.northeastern.cs5200.models.Player;
import edu.northeastern.cs5200.models.User;
import edu.northeastern.cs5200.repositories.CoachRepository;
import edu.northeastern.cs5200.repositories.PlayerRepository;
import edu.northeastern.cs5200.repositories.UserRepository;
import edu.northeastern.cs5200.services.PlayerService;
import edu.northeastern.cs5200.services.UserService;

@RestController
@CrossOrigin
public class UserController {
	
	@Autowired
	UserRepository ur;
	@Autowired
	UserService userService;
	@Autowired
	PlayerRepository playerRepo;
	@Autowired
	CoachRepository coachRepo;
	@Autowired
	PlayerService playerService;

	
	
	@PostMapping("/api/register")
	public User register(@RequestBody User user, HttpSession session) {
		session.setAttribute("currentUser", user);
		return ur.save(user);
	} 
	
	@PostMapping("/api/login")
	public User login(@RequestBody Player credential, HttpSession session) {
		User user = ur.findByCredentials(credential.getUsername(), credential.getPassword());
		if (user != null) {
			session.setAttribute("currentUser", user);
			return user;
		} else {
			return null;
		}
	}
	
	@GetMapping("/api/check-user-type/{id}")
	public String checkType(@PathVariable("id") int id) {
		User user = userService.findById(id);
		if (user instanceof Player) {
			return "player";
		} else if (user instanceof Coach) {
			return "coach";
		} else if (user instanceof Faculty) {
			return "faculty";
		} else if (user instanceof Fan) {
			return "fan";
		} else if (user instanceof Admin) {
			return "admin";
		}
		return "invalid type";
	}
	
	@PostMapping("/api/logout")
	public void logout(HttpSession session) {
		session.invalidate();
	}
	
	@PostMapping("/api/update/user")
	public User updateUser(@RequestBody User update) {
		User user = userService.findById(update.getId());
		if (user instanceof Player) {
			return playerRepo.save((Player) user);
		}
		return null;
	}
	
	
	@GetMapping("/api/remove/user/{id}")
	public String deleteUser(@PathVariable("id") int id) {
		String type = checkType(id);
		if (type.equals("player")) {
			Player player = playerService.findById(id);
			playerRepo.delete(player);
			return "player successfully reomved";
		} 
		return "invalid type";
	}
	
	
	@GetMapping("/api/search/user")
	public User findByUsername(@RequestParam(name="username") String username) {
		return ur.findByUsername(username);
	}
	
	@GetMapping("/api/search/users")
	public List<User> findAllUsers() {
		return (List<User>) ur.findAll();
	}
	
	

}
