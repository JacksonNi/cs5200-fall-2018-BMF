package edu.northeastern.cs5200.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Club {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String clubId;
	private String name;
	private String country;
	private String venueName;
	private Integer venueCapacity;
	private String city;
	
	@JsonIgnore
	@OneToMany(mappedBy="club")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Player> players;
	
	@ManyToMany(mappedBy="joined_clubs")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Tournament> joined_tournaments;
	
	@JsonIgnore
	@OneToMany(mappedBy="club")
	@LazyCollection(LazyCollectionOption.FALSE)
	@OnDelete(action=OnDeleteAction.CASCADE)
	private List<Jersey> jerseys;
	
	@JsonIgnore
	@OneToMany(mappedBy="club")
	@OnDelete(action=OnDeleteAction.CASCADE)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<ClubSeasonData> club_season_data;
	
	@OneToOne
	private Coach coach;
	
	
	public Club() {
		super();
		this.players = new ArrayList<>();
		this.joined_tournaments = new ArrayList<>();
		this.jerseys = new ArrayList<>();
		this.club_season_data = new ArrayList<>();
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public void setPlayer(Player p) {
		this.players.add(p);
		if (p.getClub() != this) {
			p.setClub(this);
		}
	}

	public List<Jersey> getJerseys() {
		return jerseys;
	}

	public void setJerseys(List<Jersey> jerseys) {
		this.jerseys = jerseys;
	}
	public void setJersey(Jersey j) {
		this.jerseys.add(j);
		if (j.getClub() != this) {
			j.setClub(this);
		}
	}

	public Coach getCoach() {
		return coach;
	}

	public void setCoach(Coach coach) {
		this.coach = coach;
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

	public String getName() {
		return name;
	}

	public void setName(String clubName) {
		this.name = clubName;
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

	public Integer getVenueCapacity() {
		return venueCapacity;
	}

	public void setVenueCapacity(Integer venueCapacity) {
		this.venueCapacity = venueCapacity;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getClubId() {
		return clubId;
	}

	public void setClubId(String clubId) {
		this.clubId = clubId;
	}

	
	
	
	

}
