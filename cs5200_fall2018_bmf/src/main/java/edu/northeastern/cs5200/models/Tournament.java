package edu.northeastern.cs5200.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Tournament {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String tournament_id;
	private String name;
	private String cname;
	private String countryCode;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name="club_tournament",
		joinColumns = @JoinColumn(name = "tournament_id"),
		inverseJoinColumns = @JoinColumn(name = "club_id")
	)
	private List<Club> joined_clubs;
	
	@ManyToOne
	private Faculty faculty;
	
	@JsonIgnore
	@OneToMany(mappedBy="tournament")
	@OnDelete(action=OnDeleteAction.CASCADE)
	private List<Season> seasons;
	

	public Tournament() {
		super();
		this.joined_clubs = new ArrayList<>();
		this.seasons = new ArrayList<>();
	}

	public List<Season> getSeasons() {
		return seasons;
	}

	public void setSeasons(List<Season> seasons) {
		this.seasons = seasons;
	}
	
	public void setSeason(Season s) {
		this.seasons.add(s);
		if (s.getTournament() != this) {
			s.setTournament(this);
		}
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
		if (!faculty.getAuthoredTours().contains(this)) {
			faculty.getAuthoredTours().add(this);
		}
	}

	public List<Club> getJoined_clubs() {
		return joined_clubs;
	}

	public void setJoined_clubs(List<Club> joined_clubs) {
		this.joined_clubs = joined_clubs;
	}
	
	public void addClub(Club c) {
		this.joined_clubs.add(c);
		if (!c.getJoined_tournaments().contains(this)) {
			c.getJoined_tournaments().add(this);
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTournament_id() {
		return tournament_id;
	}

	public void setTournament_id(String tournament_id) {
		this.tournament_id = tournament_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	
	
	
}
