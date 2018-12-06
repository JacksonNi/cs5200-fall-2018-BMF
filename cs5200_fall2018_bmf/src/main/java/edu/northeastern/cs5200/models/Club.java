package edu.northeastern.cs5200.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Club {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String clubName;
	private String country;
	private String venueName;
	private String venueCapacity;
	private String city;
	
	@ManyToMany(mappedBy="joined_clubs")
	private List<Tournament> joined_tournaments;
	
	@Enumerated(EnumType.STRING)
	private Jersey jersey;
	
	@OneToMany(mappedBy="club")
	@OnDelete(action=OnDeleteAction.CASCADE)
	private List<ClubSeasonData> club_season_data;
	
	@OneToOne(mappedBy="club")
	private Home home;
	
	@OneToOne(mappedBy="club")
	private Away away;
	
	@OneToOne
	private Coach coach;
	
	
	
	public Coach getCoach() {
		return coach;
	}

	public void setCoach(Coach coach) {
		this.coach = coach;
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

	public List<ClubSeasonData> getClub_season_data() {
		return club_season_data;
	}

	public void setClub_season_data(List<ClubSeasonData> club_season_data) {
		this.club_season_data = club_season_data;
	}
	public void setClubSeasonData(ClubSeasonData csd) {
		this.club_season_data.add(csd);
		if (csd.getClub() != this) {
			csd.setClub(this);
		}
	}

	public List<Tournament> getJoined_tournaments() {
		return joined_tournaments;
	}

	public void setJoined_tournaments(List<Tournament> joined_tournaments) {
		this.joined_tournaments = joined_tournaments;
	}
	
	public void joinTournament(Tournament t) {
		this.joined_tournaments.add(t);
		if (!t.getJoined_clubs().contains(this)) {
			t.getJoined_clubs().add(this);
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClubName() {
		return clubName;
	}

	public void setClubName(String clubName) {
		this.clubName = clubName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getVenueName() {
		return venueName;
	}

	public void setVenueName(String venueName) {
		this.venueName = venueName;
	}

	public String getVenueCapacity() {
		return venueCapacity;
	}

	public void setVenueCapacity(String venueCapacity) {
		this.venueCapacity = venueCapacity;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Jersey getJersey() {
		return jersey;
	}

	public void setJersey(Jersey jersey) {
		this.jersey = jersey;
	}
	
	
	
	
	

}
