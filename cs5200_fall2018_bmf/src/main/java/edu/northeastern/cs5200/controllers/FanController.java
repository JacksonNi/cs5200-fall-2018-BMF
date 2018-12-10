package edu.northeastern.cs5200.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.northeastern.cs5200.models.Club;
import edu.northeastern.cs5200.models.Fan;
import edu.northeastern.cs5200.models.FollowedClub;
import edu.northeastern.cs5200.models.FollowedPlayer;
import edu.northeastern.cs5200.models.Player;
import edu.northeastern.cs5200.repositories.ClubRepository;
import edu.northeastern.cs5200.repositories.FanRepository;
import edu.northeastern.cs5200.repositories.FollowedClubRepository;
import edu.northeastern.cs5200.repositories.FollowedPlayerRepository;
import edu.northeastern.cs5200.repositories.PlayerRepository;

@RestController
public class FanController {

	
	@Autowired
	FanRepository fanRepo;
	@Autowired
	ClubRepository clubRepo;
	@Autowired
	FollowedClubRepository fcRepo;
	@Autowired
	FollowedPlayerRepository fpRepo;
	@Autowired
	PlayerRepository playerRepo;
	
	
	@PostMapping("/api/register/fan")
	public Fan register(@RequestBody Fan user, HttpSession session) {
		session.setAttribute("currentUser", user);
		return fanRepo.save(user);
	}
	
	@GetMapping("/api/search/user/fans")
	public List<Fan> findAllFans() {
		return (List<Fan>) fanRepo.findAll();
	}
	
	@PutMapping("/api/update/fan/{fan_id}/follow/player")
	public Fan followPlayer(@PathVariable("fan_id") int fanId, @RequestParam(name="playerId") String playerId) {
		Fan fan = findById(fanId);
		if (fan == null) {
			return null;
		}
		FollowedPlayer fp = new FollowedPlayer();
		fp.setPlayer_id(playerId);
		fp.setFan(fan);
		fpRepo.save(fp);
		return fan;
	}
	
	@GetMapping("/api/search/fan/{fan_id}/followedplayers")
	public List<Player> findFollowedPlayers(int id) {
		Fan fan = findById(id);
		List<FollowedPlayer> followedPlayers = fan.getFollowedPlayers();
		List<Player> players = new ArrayList<>();
		for (FollowedPlayer fp: followedPlayers) {
			String playerId = fp.getPlayer_id();
			Player player = playerRepo.findPlayerByPlayerId(playerId);
			if (player != null) {
				players.add(player);
			}
		}
		return players;
	}
	
	
	@PutMapping("/api/update/fan/{fan_id}/follow/club")
	public Fan followClub(@PathVariable("fan_id") int fanId, @RequestParam(name="clubId") String clubId) {
		Fan fan = findById(fanId);
		if (fan == null) {
			return null;
		}
		FollowedClub fc = new FollowedClub();
		fc.setClub_id(clubId);
		fc.setFan(fan);
		fcRepo.save(fc);
		return fan;
	}
	
	
	
	@GetMapping("/api/search/fan/{fan_id}/followedclubs")
	public List<Club> findFollowedClubs(@PathVariable("fan_id") int id) {
		Fan fan = findById(id);
		List<FollowedClub> followedClubs = fan.getFollowedClubs();
		List<Club> clubs = new ArrayList<>();
		for (FollowedClub fc: followedClubs) {
			String clubId = fc.getClub_id();
			Club club = clubRepo.findClubByClubId(clubId);
			if (club != null) {
				clubs.add(club);				
			}
		}
		return clubs;
	}
	
	
	public Fan findById(int id) {
		Optional<Fan> ofan = fanRepo.findById(id);
		if (ofan.isPresent()) {
			return ofan.get();
		}
		return null;
	}
	
}
