package hh.palvelinohj.swallow;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import hh.palvelinohj.swallow.domain.Dancegroup;
import hh.palvelinohj.swallow.domain.DancegroupRepository;
import hh.palvelinohj.swallow.domain.InstructorRepository;
import hh.palvelinohj.swallow.domain.Place;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DancegroupRepositoryTest {
	
		@Autowired
	    private DancegroupRepository repository;
		
		@Autowired
	    private InstructorRepository irepository;
		
		@Test
		public void findByNameShouldReturnDancegroup() {
			List<Dancegroup> groups = repository.findByName("Satubaletti");
			
			assertThat(groups).hasSize(1);
			assertThat(groups.get(0).getAge()).isEqualTo("3-4 vuotta");
			
		}
		
		@Test
	    public void createNewDancegroup() {
	    	Dancegroup group = new Dancegroup("HipHop", "8-10 vuotta",  "ryhmän kuvaus 3", "to 18-19" , 100, 12, 12,  new Place("Koulu 2", "Osoite 3"), irepository.findByLastName("Karhu").get(0));
	    	repository.save(group);
	    	assertThat(group.getDancegroupId()).isNotNull();
	    } 
		
		@Test
	    public void deleteDancegroup() {
	    	Dancegroup group = new Dancegroup("HipHop", "8-10 vuotta",  "ryhmän kuvaus 3", "to 18-19" , 100, 12, 12,  new Place("Koulu 2", "Osoite 3"), irepository.findByLastName("Karhu").get(0));
	    	repository.save(group);
	    	long idb = group.getDancegroupId();
	    	repository.delete(group);
	    	assertThat(repository.findById(idb)).isEmpty();
	    } 
	}


