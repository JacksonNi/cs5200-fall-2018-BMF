package edu.northeastern.cs5200.models;

import javax.persistence.*;

@Entity
public class News {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String fact;
	
	@ManyToOne
	private Match match;

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
		if (!match.getNewses().contains(this)) {
			match.getNewses().add(this);
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFact() {
		return fact;
	}

	public void setFact(String fact) {
		this.fact = fact;
	}
	
	
	

}
