package edu.northeastern.cs5200.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Match {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String weather;
	private String venue;
	private int attendance;
	private String type;
	private int number;
	
	@ManyToOne
	private Season season;
	
	@OneToMany(mappedBy="match")
	private List<News> newses;
	
	@OneToOne(mappedBy="match")
	private Home home;
	
	@OneToOne(mappedBy="match")
	private Away away;
	
	

	public Away getAway() {
		return away;
	}

	public void setAway(Away away) {
		this.away = away;
	}

	public Home getHome() {
		return home;
	}

	public void setHome(Home home) {
		this.home = home;
	}

	public List<News> getNewses() {
		return newses;
	}

	public void setNewses(List<News> news) {
		this.newses = news;
	}
	public void setNews(News news) {
		this.newses.add(news);
		if (news.getMatch() != this) {
			news.setMatch(this);
		}
	}

	public Season getSeason() {
		return season;
	}

	public void setSeason(Season season) {
		this.season = season;
		if (!season.getMatches().contains(this)) {
			season.getMatches().add(this);
		}
	}
	
	

}
