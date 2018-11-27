package edu.northeastern.cs5200.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Season {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String sname;
	private int scheduled;
	private int played;
	private String max_coverage_level;
	private int max_covered;
}
