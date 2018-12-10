package edu.northeastern.cs5200.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.northeastern.cs5200.models.News;
import edu.northeastern.cs5200.repositories.NewsRepository;

@RestController
@CrossOrigin
public class NewsController {
	
	@Autowired
	NewsRepository newsRepo;
	
	@GetMapping("/api/search/news")
	public List<News> getNews() {
		return (List<News>) newsRepo.findAll();
	}

}
