package edu.northeastern.cs5200.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Home {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String formation;
	private int score;
	private int ball_possession;
	private int corner_kicks;
	private int free_kicks;
	private int shots_on_target;
	private int fouls;
	private int goal_kicks;
	private int yellow_cards;
	
	@OneToOne
	@JoinColumn(name = "match_id")
	private Match match;
	

	private String club_id;
	
	@OneToMany(mappedBy="home")
	@OnDelete(action=OnDeleteAction.CASCADE)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<HomeLineUp> lineUps;
	

	public Home() {
		super();
		this.lineUps = new ArrayList<>();
	}

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



	public String getClub_id() {
		return club_id;
	}

	public void setClub_id(String club_id) {
		this.club_id = club_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFormation() {
		return formation;
	}

	public void setFormation(String formation) {
		this.formation = formation;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getBall_possession() {
		return ball_possession;
	}

	public void setBall_possession(int ball_possession) {
		this.ball_possession = ball_possession;
	}

	public int getCorner_kicks() {
		return corner_kicks;
	}

	public void setCorner_kicks(int corner_kicks) {
		this.corner_kicks = corner_kicks;
	}

	public int getFree_kicks() {
		return free_kicks;
	}

	public void setFree_kicks(int free_kicks) {
		this.free_kicks = free_kicks;
	}

	public int getShots_on_target() {
		return shots_on_target;
	}

	public void setShots_on_target(int shots_on_target) {
		this.shots_on_target = shots_on_target;
	}

	public int getFouls() {
		return fouls;
	}

	public void setFouls(int fouls) {
		this.fouls = fouls;
	}

	public int getGoal_kicks() {
		return goal_kicks;
	}

	public void setGoal_kicks(int goal_kicks) {
		this.goal_kicks = goal_kicks;
	}

	public int getYEllow_cards() {
		return yellow_cards;
	}

	public void setYEllow_cards(int ellow_cards) {
		this.yellow_cards = ellow_cards;
	}
	
}
