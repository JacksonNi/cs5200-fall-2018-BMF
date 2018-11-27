package edu.northeastern.cs5200.models;

import javax.persistence.Entity;

@Entity
public class Player extends User {
	
	private String nationality;
	private int height;
	private int weight;
	private int jerseyNumber;
	private String gender;
	private String preferredFoot;
	private String role;
	
	
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
	
}
