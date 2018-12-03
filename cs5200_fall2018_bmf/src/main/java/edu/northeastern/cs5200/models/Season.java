package edu.northeastern.cs5200.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Season {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String sname;
	private int scheduled;
	private int played;
	private String max_coverage_level;
	private int max_covered;
	
	@ManyToOne
	private Tournament tournament;
	
	@OneToMany(mappedBy="season")
	@OnDelete(action=OnDeleteAction.CASCADE)
	private List<ClubSeasonData> club_season_data;
	
	@OneToMany(mappedBy="season")
	@OnDelete(action=OnDeleteAction.CASCADE)
	private List<Match> matches;
	
	
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
}
