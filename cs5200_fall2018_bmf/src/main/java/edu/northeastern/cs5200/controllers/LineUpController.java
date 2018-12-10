package edu.northeastern.cs5200.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.northeastern.cs5200.models.Away;
import edu.northeastern.cs5200.models.AwayLineUp;
import edu.northeastern.cs5200.models.Home;
import edu.northeastern.cs5200.models.HomeLineUp;
import edu.northeastern.cs5200.models.LineUp;
import edu.northeastern.cs5200.repositories.AwayLineUpRepository;
import edu.northeastern.cs5200.repositories.HomeLineUpRepository;
import edu.northeastern.cs5200.services.AwayService;
import edu.northeastern.cs5200.services.HomeService;

@RestController
@CrossOrigin
public class LineUpController {
	
	@Autowired
	HomeLineUpRepository hlpRepo;
	@Autowired
	AwayLineUpRepository alpRepo;
	@Autowired
	HomeService homeService;
	@Autowired
	AwayService awayService;


	@PostMapping("/api/create/home/{id}/lineup")
	public LineUp createHomeLineUp(@RequestBody HomeLineUp lp, @PathVariable("id") int homeId) {
		Home home = homeService.findById(homeId);
		lp.setHome(home);
		return hlpRepo.save(lp);
	}
	
	@PostMapping("/api/create/away/{id}/lineup")
	public LineUp createAwayLineUp(@RequestBody AwayLineUp lp, @PathVariable("id") int awayId) {
		Away away = awayService.findById(awayId);
		lp.setAway(away);
		return alpRepo.save(lp);
	}
	
	@GetMapping("/api/search/home/{id}/lineups")
	public List<HomeLineUp> findLineUpsForHome(@PathVariable("id") int homeId) {
		Home home = homeService.findById(homeId);
		return home.getLineUps();
	}
	
	@GetMapping("/api/search/away/{id}/lineups")
	public List<AwayLineUp> findLineUpsForAway(@PathVariable("id") int awayId) {
		Away away = awayService.findById(awayId);
		return away.getLineUps();
	}
	
	

}
