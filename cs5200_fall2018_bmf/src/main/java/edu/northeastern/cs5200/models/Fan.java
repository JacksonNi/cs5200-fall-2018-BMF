package edu.northeastern.cs5200.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Fan extends User {

	private boolean membership;
	
	@OneToMany(mappedBy="fan")
	@OnDelete(action=OnDeleteAction.CASCADE)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<FollowedClub> followedClubs;
	
	@OneToMany(mappedBy="fan")
	@OnDelete(action=OnDeleteAction.CASCADE)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<FollowedPlayer> followedPlayers;
	

	public Fan() {
		super();
		this.followedClubs = new ArrayList<>();
		this.followedPlayers = new ArrayList<>();
	}

	public List<FollowedPlayer> getFollowedPlayers() {
		return followedPlayers;
	}

	public void setFollowedPlayers(List<FollowedPlayer> followedPlayers) {
		this.followedPlayers = followedPlayers;
	}
	public void setFollowedPlayer(FollowedPlayer fp) {
		this.followedPlayers.add(fp);
		if (fp.getFan() != this) {
			fp.setFan(this);
		}
	}

	public boolean isMembership() {
		return membership;
	}

	public void setMembership(boolean membership) {
		this.membership = membership;
	}

	public List<FollowedClub> getFollowedClubs() {
		return followedClubs;
	}

	public void setFollowedClubs(List<FollowedClub> followedClubs) {
		this.followedClubs = followedClubs;
	}
	public void setFollowedClub(FollowedClub fc) {
		this.followedClubs.add(fc);
		if (fc.getFan() != this) {}
		fc.setFan(this);
	}
	
	
	
	
	
}
