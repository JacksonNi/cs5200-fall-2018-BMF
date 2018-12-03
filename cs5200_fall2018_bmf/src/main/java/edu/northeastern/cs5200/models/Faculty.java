package edu.northeastern.cs5200.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Faculty extends User{
	
	@OneToMany(mappedBy="faculty")
	private List<Tournament> authoredTours;

	public List<Tournament> getAuthoredTours() {
		return authoredTours;
	}

	public void setAuthoredTours(List<Tournament> authoredTours) {
		this.authoredTours = authoredTours;
	}
	public void setAuthoredTour(Tournament t) {
		this.authoredTours.add(t);
		if (t.getFaculty() != this) {
			t.setFaculty(this);
		}
	}
	
	
}
