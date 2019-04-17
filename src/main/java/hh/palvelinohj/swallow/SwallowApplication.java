package hh.palvelinohj.swallow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.palvelinohj.swallow.domain.Dancegroup;
import hh.palvelinohj.swallow.domain.DancegroupRepository;
import hh.palvelinohj.swallow.domain.Instructor;
import hh.palvelinohj.swallow.domain.InstructorRepository;
import hh.palvelinohj.swallow.domain.Place;
import hh.palvelinohj.swallow.domain.PlaceRepository;
import hh.palvelinohj.swallow.domain.User;
import hh.palvelinohj.swallow.domain.UserRepository;

@SpringBootApplication
public class SwallowApplication {

	private static final Logger log = LoggerFactory.getLogger(SwallowApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(SwallowApplication.class, args);
	}

	@Bean
	public CommandLineRunner groupDemo(DancegroupRepository drepository, PlaceRepository prepository, InstructorRepository irepository, UserRepository urepository) {
		return (args) -> {
			log.info("save a couplse of places");
			prepository.save(new Place("Urheilutalo 1", "Osoite 1"));
			prepository.save(new Place("Koulu 1", "Osoite 2"));
			
			log.info("save a couple of instructors");
			irepository.save(new Instructor("Anna", "Miekka"));
			irepository.save(new Instructor("Leena", "Karhu"));
			
			// Create users: admin/adminadmin user/useruser
			User user1 = new User("maria.kukkonen@gmail.com", "$2a$04$hFxjj6Y41fiP9RuaP5./feAR6G99wZGcNE8DuLnnGgmXFQ8QRbtxS", "Maria", "Kukkonen", 2013, "USER");
			User user3 = new User("minna.lehtinen@gmail.com", "$2a$04$hFxjj6Y41fiP9RuaP5./feAR6G99wZGcNE8DuLnnGgmXFQ8QRbtxS", "Minna", "Lehtinen", 2015, "USER");
			User user4 = new User("iris.luoma@gmail.com", "$2a$04$hFxjj6Y41fiP9RuaP5./feAR6G99wZGcNE8DuLnnGgmXFQ8QRbtxS", "Iris", "Luoma", 2014, "USER");
			User user2 = new User("admin", "$2a$04$p/JiAezhcjGaxz.SuR48z.4rA6UmrSFWVKuyRZTefpgS7j7mx1RAq", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			
			log.info("save a couplse of groups");
			Dancegroup group1 = new Dancegroup("Satubaletti", "3-4 vuotta", "ryhmän kuvaus 1", "ke 17:00-18:00", 90, 15, 13, prepository.findByName("Urheilutalo 1").get(0), irepository.findByLastName("Karhu").get(0));
			drepository.save(group1);
			Dancegroup group2 = new Dancegroup("Tanssimix", "6-8 vuotta", "ryhmän kuvaus 2", "pe 17:00-18:00", 90, 15, 14, prepository.findByName("Urheilutalo 1").get(0), irepository.findByLastName("Miekka").get(0) );
			drepository.save(group2);
			
			user1.setGroup(group1);
			user3.setGroup(group2);
			user4.setGroup(group1);
			urepository.save(user1);
			urepository.save(user3);
			urepository.save(user4);
			
			
			log.info("fetch all groups");
				for (Dancegroup group : drepository.findAll()) {
					log.info(group.toString());
				}
		};
	}
}
