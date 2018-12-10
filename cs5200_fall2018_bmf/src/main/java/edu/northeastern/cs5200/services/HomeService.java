package edu.northeastern.cs5200.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.northeastern.cs5200.models.Home;
import edu.northeastern.cs5200.repositories.HomeRepository;

@Service
public class HomeService {
	
	@Autowired
	HomeRepository homeRepo;
	
	public Home findById(int id) {
		Optional<Home> oHome = homeRepo.findById(id);
		if (oHome.isPresent()) {
			return oHome.get();
		}
		return null;
	}

}
