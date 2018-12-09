package edu.northeastern.cs5200.models;

import javax.persistence.*;

@Entity
public class LineUp {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int substitude_in;
	private int substitude_out;
	private int goals_scored;
	private int assists;
	private int own_goals;
	private int yellow_cards;
	private int red_cards;
	private String player_id;
	

	private String position;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public String getPlayer_id() {
		return player_id;
	}

	public void setPlayer_id(String player_id) {
		this.player_id = player_id;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getSubstitude_in() {
		return substitude_in;
	}

	public void setSubstitude_in(int substitude_in) {
		this.substitude_in = substitude_in;
	}

	public int getSubstitude_out() {
		return substitude_out;
	}

	public void setSubstitude_out(int substitude_out) {
		this.substitude_out = substitude_out;
	}

	public int getGoals_scored() {
		return goals_scored;
	}

	public void setGoals_scored(int goals_scored) {
		this.goals_scored = goals_scored;
	}

	public int getAssists() {
		return assists;
	}

	public void setAssists(int assists) {
		this.assists = assists;
	}

	public int getOwn_goals() {
		return own_goals;
	}

	public void setOwn_goals(int own_goals) {
		this.own_goals = own_goals;
	}

	public int getYellow_cards() {
		return yellow_cards;
	}

	public void setYellow_cards(int yellow_cards) {
		this.yellow_cards = yellow_cards;
	}

	public int getRed_cards() {
		return red_cards;
	}

	public void setRed_cards(int red_cards) {
		this.red_cards = red_cards;
	}
	
	

}
