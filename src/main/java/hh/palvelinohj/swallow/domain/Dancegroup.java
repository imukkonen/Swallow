package hh.palvelinohj.swallow.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Dancegroup {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long dancegroupId;
	private String name;
	private String age; //ryhmän lapsien ikä (3-4,4-5,5-6 jne)
	private String description;
	private String timetable; //treenan viikkopäivä ja aika
	private double price;
	private int quantity; //ryhmän paikkojen määrä
	private int freeplaces; //ryhmän vapaita paikkoja
	
	@ManyToOne
	 @JsonIgnore
	  @JoinColumn(name = "placeId")
	 private Place place;
	
	@ManyToOne
	 @JsonIgnore
	  @JoinColumn(name = "instructorId")
	 private Instructor instructor;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "group")
	private List<User> users;
	
	public Dancegroup() {
		super();
		
	}

	public Dancegroup(String name, String age, String description, String timetable, double price, int quantity,
			int freeplaces) {
		super();
		this.name = name;
		this.age = age;
		this.description = description;
		this.timetable = timetable;
		this.price = price;
		this.quantity = quantity;
		this.freeplaces = freeplaces;
	}

	public Dancegroup(String name, String age, String description, String timetable, double price, int quantity,
			int freeplaces, Place place, Instructor instructor) {
		super();
		this.name = name;
		this.age = age;
		this.description = description;
		this.timetable = timetable;
		this.price = price;
		this.quantity = quantity;
		this.freeplaces = freeplaces;
		this.place = place;
		this.instructor = instructor;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public long getDancegroupId() {
		return dancegroupId;
	}

	public void setDancegroupId(long dancegroupId) {
		this.dancegroupId = dancegroupId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTimetable() {
		return timetable;
	}

	public void setTimetable(String timetable) {
		this.timetable = timetable;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getFreeplaces() {
		return freeplaces;
	}

	public void setFreeplaces(int freeplaces) {
		this.freeplaces = freeplaces;
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	@Override
	public String toString() {
		String retString= "Dancegroup [id=" + dancegroupId + ", name=" + name + ", age=" + age + ", description=" + description + ", timetable=" + timetable
				+ ", price=" + price + ", quantity=" + quantity + ", freeplaces=" + freeplaces + "]";
		if (this.place != null) retString+= ", place=" +this.place;
		if (this.instructor != null) retString+= ", insrtuctor=" +this.instructor;
		return retString;
	}
	
	
	
	
}
