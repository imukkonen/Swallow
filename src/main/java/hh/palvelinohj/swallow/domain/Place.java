package hh.palvelinohj.swallow.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Place {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long placeId;
	private String name;
	private String address;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "place")
	private List<Dancegroup> groups;

	public Place() {
		super();
			}

	public Place(String name, String address) {
		super();
		this.name = name;
		this.address = address;
	}

	public long getPlaceId() {
		return placeId;
	}

	public void setPlaceId(long placeId) {
		this.placeId = placeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Dancegroup> getGroups() {
		return groups;
	}

	public void setGroups(List<Dancegroup> groups) {
		this.groups = groups;
	}

	
	
	
	
}
