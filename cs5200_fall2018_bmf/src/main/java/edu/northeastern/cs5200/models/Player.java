package edu.northeastern.cs5200.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Player extends User {
	
	private String nationality;
	private int height;
	private int weight;
	private int jerseyNumber;
	private String gender;
	private String preferredFoot;
	
	@OneToMany(mappedBy="player")
	private List<Role> roles;
		
	
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public void setRole(Role r) {
		this.roles.add(r);
		if (r.getPlayer() != this) {
			r.setPlayer(this);
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

	
	
	
}
