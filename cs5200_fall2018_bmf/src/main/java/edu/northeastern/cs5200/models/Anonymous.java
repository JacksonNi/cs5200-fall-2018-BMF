package edu.northeastern.cs5200.models;

import javax.persistence.Entity;

@Entity
public class Anonymous extends User {
	private boolean userAgreement;
}
