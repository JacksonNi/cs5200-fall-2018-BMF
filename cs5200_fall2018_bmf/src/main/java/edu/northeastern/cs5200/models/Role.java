package edu.northeastern.cs5200.models;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Role {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String type;
	private boolean active;
	private Date start;
	private int jerseyNumber;
	private int club;
	
	@ManyToOne
	private Player player;

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
		if (!player.getRoles().contains(this)) {
			player.getRoles().add(this);
		}
	}
}
