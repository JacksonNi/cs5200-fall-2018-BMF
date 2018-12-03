package edu.northeastern.cs5200.models;

import javax.persistence.*;

@Entity
@Table(name="admins")
public class Admin extends User {

	private boolean CRUDAuthorized;
	
}
