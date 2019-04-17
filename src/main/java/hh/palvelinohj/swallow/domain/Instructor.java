package hh.palvelinohj.swallow.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Instructor {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long instructorId;
    private String firstName;
    private String lastName;
	
    
   @OneToMany(cascade = CascadeType.ALL, mappedBy = "instructor")
	private List<Dancegroup> groups;
    
    public Instructor() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    public Instructor(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public long getInstructorId() {
		return instructorId;
	}

	public void setInstructorId(long instructorId) {
		this.instructorId = instructorId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Dancegroup> getGroups() {
		return groups;
	}

	public void setGroups(List<Dancegroup> groups) {
		this.groups = groups;
	}

    
	
    
}
