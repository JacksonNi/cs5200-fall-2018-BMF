package edu.northeastern.cs5200.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Player extends User {
	
	private String player_id;
	private String nationality;
	private int height;
	private int weight;
	private int jerseyNumber;
	private String gender;
	private String preferredFoot;
	private String type;
	
	@ManyToOne
	private Club club;
	

	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Club getClub() {
		return club;
	}
	public void setClub(Club club) {
		this.club = club;
		if (!club.getPlayers().contains(this)) {
			club.getPlayers().add(this);
		}
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getJerseyNumber() {
		return jerseyNumber;
	}
	public void setJerseyNumber(int jerseyNumber) {
		this.jerseyNumber = jerseyNumber;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPreferredFoot() {
		return preferredFoot;
	}
	public void setPreferredFoot(String preferredFoot) {
		this.preferredFoot = preferredFoot;
	}
	public String getPlayer_id() {
		return player_id;
	}
	public void setPlayer_id(String player_id) {
		this.player_id = player_id;
	}


	
	
	
}
