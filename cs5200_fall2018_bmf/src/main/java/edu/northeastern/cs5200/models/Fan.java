package edu.northeastern.cs5200.models;

import javax.persistence.Entity;

@Entity
public class Fan extends User {

	private boolean membership;
	private String[] followedClub;
	private String[] followedPlayer;
	private String[] followedCoach;
}
