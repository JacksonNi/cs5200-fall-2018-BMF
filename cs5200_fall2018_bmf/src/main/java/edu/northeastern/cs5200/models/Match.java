package edu.northeastern.cs5200.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="matches")
public class Match {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String match_id;
	private String venue;
	private String scheduled;
	private String status;
	private String winner_id;
	
	
	
	@ManyToOne
	private Season season;
	
	@JsonIgnore
	@OneToMany(mappedBy="match")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<News> newses;
	
	@JsonIgnore
	@OneToOne(mappedBy="match")
	private Home home;
	
	@JsonIgnore
	@OneToOne(mappedBy="match")
	private Away away;
	
	
	public Match() {
		super();
		this.newses = new ArrayList<>();
	}

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMatch_id() {
		return match_id;
	}

	public void setMatch_id(String match_id) {
		this.match_id = match_id;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public String getScheduled() {
		return scheduled;
	}

	public void setScheduled(String scheduled) {
		this.scheduled = scheduled;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getWinner_id() {
		return winner_id;
	}

	public void setWinner_id(String winner_id) {
		this.winner_id = winner_id;
	}
	
	

}
