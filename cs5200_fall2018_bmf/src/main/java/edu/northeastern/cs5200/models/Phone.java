package edu.northeastern.cs5200.models;

import javax.persistence.*;

@Entity
public class Phone {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String phone;
	private boolean primary_phone;
	
	@ManyToOne
	private User user;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isPrimary_phone() {
		return primary_phone;
	}
	public void setPrimary_phone(boolean primary_phone) {
		this.primary_phone = primary_phone;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
		if (!user.getPhones().contains(this)) {
			user.setPhone(this);
		}
	}
	
}
