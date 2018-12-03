package edu.northeastern.cs5200.models;

import javax.persistence.*;

@Entity
public class AwayLineUp extends LineUp {

	@ManyToOne
	private Away away;

	public Away getAway() {
		return away;
	}

	public void setAway(Away away) {
		this.away = away;
		if (!away.getLineUps().contains(this)) {
			away.getLineUps().add(this);
		}
	}
	
	
	
}
