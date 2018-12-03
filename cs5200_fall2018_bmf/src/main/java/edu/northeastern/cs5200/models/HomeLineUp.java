package edu.northeastern.cs5200.models;

import javax.persistence.*;

@Entity
public class HomeLineUp extends LineUp {
	
	@ManyToOne
	private Home home;

	public Home getHome() {
		return home;
	}

	public void setHome(Home home) {
		this.home = home;
		if (!home.getLineUps().contains(this)) {
			home.getLineUps().add(this);
		}
	}
	
	

}
