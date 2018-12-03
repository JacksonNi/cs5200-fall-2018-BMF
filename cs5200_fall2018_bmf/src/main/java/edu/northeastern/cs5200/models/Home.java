package edu.northeastern.cs5200.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Home {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String qualifier;
	private String formation;
	
	@OneToOne
	@JoinColumn(name = "match_id")
	private Match match;
	
	@OneToOne
	@JoinColumn(name = "club_id")
	private Club club;
	
	@OneToMany(mappedBy="home")
	@OnDelete(action=OnDeleteAction.CASCADE)
	private List<HomeLineUp> lineUps;
	

	public List<HomeLineUp> getLineUps() {
		return lineUps;
	}

	public void setLineUps(List<HomeLineUp> lineUps) {
		this.lineUps = lineUps;
	}
	public void setLineUp(HomeLineUp lp) {
		this.lineUps.add(lp);
		if (lp.getHome() != this) {
			lp.setHome(this);
		}
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}
	
}
