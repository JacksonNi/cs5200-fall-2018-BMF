package edu.northeastern.cs5200.models;

import javax.persistence.Entity;

@Entity
public class Coach extends User {
	
	private String nationality;

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
}
