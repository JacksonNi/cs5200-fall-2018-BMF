package edu.northeastern.cs5200.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tournament {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String tname;
	private String cname;
	private String countryCode;
}
