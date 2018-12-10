package edu.northeastern.cs5200.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.northeastern.cs5200.models.Club;
import edu.northeastern.cs5200.models.ClubSeasonData;
import edu.northeastern.cs5200.models.Season;
import edu.northeastern.cs5200.repositories.ClubSeasonDataRepository;
import edu.northeastern.cs5200.services.ClubService;
import edu.northeastern.cs5200.services.SeasonService;

@RestController
@CrossOrigin
public class ClubDataController {
	
	@Autowired
	ClubService clubService;
	@Autowired
	SeasonService seasonService;
	@Autowired
	ClubSeasonDataRepository dataRepo;
	
	
	
	@GetMapping("/api/search/season-data")
	public List<ClubSeasonData> getClubData(@RequestParam(name="clubId") int clubId) {
		Club club = clubService.findById(clubId);
		return club.getClub_season_data();
	}

	
	
	
	
	public ClubSeasonData getClulSeasonResult(int clubId, int seasonId) {
		Club club = clubService.findById(clubId);
		Season season = seasonService.findById(seasonId);
		return dataRepo.findDataByClubAndSeason(club, season);
	}
	
	

}
