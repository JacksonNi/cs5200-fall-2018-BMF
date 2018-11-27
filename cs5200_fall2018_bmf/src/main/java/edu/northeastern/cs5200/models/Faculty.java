package edu.northeastern.cs5200.models;

import javax.persistence.*;

@Entity
public class Faculty extends User{
	
	private boolean Tauhorized;

	public boolean isTauhorized() {
		return Tauhorized;
	}

	public void setTauhorized(boolean tauhorized) {
		Tauhorized = tauhorized;
	}
	
	
}
