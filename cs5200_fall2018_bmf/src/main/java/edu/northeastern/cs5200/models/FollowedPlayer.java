package edu.northeastern.cs5200.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class FollowedPlayer {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String player_id;
	
	@ManyToOne
	private Fan fan;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Fan getFan() {
		return fan;
	}

	public void setFan(Fan fan) {
		this.fan = fan;
		if (!fan.getFollowedPlayers().contains(this)) {
			fan.getFollowedPlayers().add(this);
		}
	}

	public String getPlayer_id() {
		return player_id;
	}

	public void setPlayer_id(String player_id) {
		this.player_id = player_id;
	}
	
}
