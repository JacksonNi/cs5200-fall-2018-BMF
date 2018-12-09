package edu.northeastern.cs5200.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Season {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String season_id;
	private String sname;
	private int scheduled;
	private int played;
	private String max_coverage_level;
	private int max_covered;
	private Date start;
	private Date end;
	private String year;
	private boolean current;
	
	@ManyToOne
	private Tournament tournament;
	
	@OneToMany(mappedBy="season")
	@OnDelete(action=OnDeleteAction.CASCADE)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<ClubSeasonData> club_season_data;
	
	@OneToMany(mappedBy="season")
	@OnDelete(action=OnDeleteAction.CASCADE)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Match> matches;
	
	
	
	
	public Season() {
		super();
		this.club_season_data = new ArrayList<>();
		this.matches = new ArrayList<>();
	}

	public List<Match> getMatches() {
		return matches;
	}

	public void setMatches(List<Match> matches) {
		this.matches = matches;
	}
	public void setMatch(Match m) {
		this.matches.add(m);
		if (m.getSeason() != this) {
			m.setSeason(this);
		}
	}

	public List<ClubSeasonData> getClub_season_data() {
		return club_season_data;
	}

	public void setClub_season_goals(List<ClubSeasonData> club_season_goals) {
		this.club_season_data = club_season_goals;
	}
	public void setClubSeasonGoal(ClubSeasonData csd) {
		this.club_season_data.add(csd);
		if (csd.getSeason() != this) {
			csd.setSeason(this);
		}
	}

	public Tournament getTournament() {
		return tournament;
	}

	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
		if (!tournament.getSeasons().contains(this)) {
			tournament.getSeasons().add(this);
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSeason_id() {
		return season_id;
	}

	public void setSeason_id(String season_id) {
		this.season_id = season_id;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public int getScheduled() {
		return scheduled;
	}

	public void setScheduled(int scheduled) {
		this.scheduled = scheduled;
	}

	public int getPlayed() {
		return played;
	}

	public void setPlayed(int played) {
		this.played = played;
	}

	public String getMax_coverage_level() {
		return max_coverage_level;
	}

	public void setMax_coverage_level(String max_coverage_level) {
		this.max_coverage_level = max_coverage_level;
	}

	public int getMax_covered() {
		return max_covered;
	}

	public void setMax_covered(int max_covered) {
		this.max_covered = max_covered;
	}

	public void setClub_season_data(List<ClubSeasonData> club_season_data) {
		this.club_season_data = club_season_data;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public boolean isCurrent() {
		return current;
	}

	public void setCurrent(boolean current) {
		this.current = current;
	}
	
	
	
}
