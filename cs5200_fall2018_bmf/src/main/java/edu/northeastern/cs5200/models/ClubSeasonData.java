package edu.northeastern.cs5200.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ClubSeasonData {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int goalsScored;
	private int goalsConceded;
	private int matchesWon;
	private int matchesPlayed;
	private int matchesDrawn;
	private int matchesLost;
	private int rank;
	private String formTotal;
	
	@ManyToOne
	private Season season;
	
	@ManyToOne
	private Club club;
	

	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
		if (!club.getClub_season_data().contains(this)) {
			club.getClub_season_data().add(this);
		}
	}

	public Season getSeason() {
		return season;
	}

	public void setSeason(Season season) {
		this.season = season;
		if (!season.getClub_season_data().contains(this)) {
			season.getClub_season_data().add(this);
		}
	}
	

}
